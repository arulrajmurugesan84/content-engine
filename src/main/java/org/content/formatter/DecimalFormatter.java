package org.content.formatter;

import org.content.config.FormatConfig;

import java.text.DecimalFormat;

public final class DecimalFormatter implements ValueFormatter {

    @Override
    public boolean supports(String type) {
        return "decimal".equalsIgnoreCase(type);
    }

    @Override
    public Object format(Object value, FormatConfig config) {
        var formatter = new DecimalFormat();
        formatter.setMinimumFractionDigits(config.fraction());
        formatter.setMaximumFractionDigits(config.fraction());
        return formatter.format(value);
    }
}

