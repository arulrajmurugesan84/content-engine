package org.content.formatter;

import org.content.config.FormatConfig;

public interface ValueFormatter {
    boolean supports(String type);
    Object format(Object value, FormatConfig config);
}

