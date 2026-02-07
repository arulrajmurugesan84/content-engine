package org.content.service;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import java.io.IOException;
import java.util.Map;

public final class HandlebarsTemplateEngine implements TemplateEngine {

    private final Handlebars handlebars = new Handlebars();

    @Override
    public String render(String template, Map<String, Object> context) {
        try {
            handlebars.registerHelper("upper", (templateContext, options) ->
                    templateContext.toString().toUpperCase());

            handlebars.registerHelper("default", (value, options) ->
                    value == null || value.toString().isBlank()
                            ? options.param(0)
                            : value);

            handlebars.registerHelper("exists", (value, options) ->
                    value != null ? options.fn() : options.inverse());

            Template compiled = handlebars.compileInline(template);
            return compiled.apply(context);
        } catch (IOException e) {
            throw new RuntimeException("Template rendering failed", e);
        }
    }
}
