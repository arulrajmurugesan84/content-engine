package org.content.config;

public record FormatConfig(
        String type,
        String locale,
        String currency,
        Integer fraction
) {}
