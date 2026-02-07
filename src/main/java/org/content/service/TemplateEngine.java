package org.content.service;

import java.util.Map;

public interface TemplateEngine {
    String render(String template, Map<String, Object> context);
}
