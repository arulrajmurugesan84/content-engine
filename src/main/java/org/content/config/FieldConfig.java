package org.content.config;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = false)
public record FieldConfig(
        String fieldName,
        String path,
        String type,
        boolean required,
        @JsonAlias("default")
        String defaultValue,
        List<String> conditionalPaths,
        FormatConfig format,
        List<FieldConfig> fields,
        String expression
) {}

