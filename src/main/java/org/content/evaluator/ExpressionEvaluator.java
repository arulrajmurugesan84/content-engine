package org.content.evaluator;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;

public final class ExpressionEvaluator {

    private final ExpressionParser parser = new SpelExpressionParser();

    public Object evaluate(String expression, Map<String, Object> context) {
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        context.forEach(ctx::setVariable);
        return parser.parseExpression(expression).getValue(ctx);
    }
}

