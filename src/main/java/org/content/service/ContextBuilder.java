package org.content.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.content.config.DataConfig;
import org.content.config.FieldConfig;
import org.content.config.FormatConfig;
import org.content.evaluator.ExpressionEvaluator;
import org.content.exception.ValidationException;
import org.content.formatter.ValueFormatter;
import org.content.resolver.JsonPathValueResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ContextBuilder {

    private final JsonPathValueResolver resolver;
    private final ExpressionEvaluator evaluator;
    private final List<ValueFormatter> formatters;

    public ContextBuilder(JsonPathValueResolver resolver,
                          ExpressionEvaluator evaluator,
                          List<ValueFormatter> formatters) {
        this.resolver = resolver;
        this.evaluator = evaluator;
        this.formatters = formatters;
    }

    public Map<String, Object> build(DataConfig config, JsonNode root) {
        Map<String, Object> ctx = new HashMap<>();

        // Phase 1 - JSONPath / conditional
        for (FieldConfig f : config.dataConfig()) {
            if (f.expression() == null) {
                Object value = f.conditionalPaths() != null
                        ? resolver.resolveFirst(root, f.conditionalPaths()).orElse(null)
                        : resolver.resolve(root, f.path());
                if (value == null && f.defaultValue() != null) value = f.defaultValue();
                ctx.put(f.fieldName(), applyFormat(value, f.format()));
            }
        }

        // Phase 2 - Expressions
        for (FieldConfig f : config.dataConfig()) {
            if (f.expression() != null) {
                Object value = evaluator.evaluate(f.expression(), ctx);
                ctx.put(f.fieldName(), applyFormat(value, f.format()));
            }
        }

        // Phase 3 - Validate required fields
        for (FieldConfig f : config.dataConfig()) {
            if (f.required() && !ctx.containsKey(f.fieldName())) {
                throw new ValidationException("Missing required field: " + f.fieldName());
            }
        }

        return Map.copyOf(ctx);
    }

    private Object applyFormat(Object value, FormatConfig format) {
        if (value == null || format == null) return value;
        return formatters.stream()
                .filter(f -> f.supports(format.type()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "No formatter found for type: " + format.type()))
                .format(value, format);
    }
}
