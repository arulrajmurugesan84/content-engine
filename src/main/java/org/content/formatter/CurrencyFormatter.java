package org.content.formatter;

import org.content.config.FormatConfig;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public final class CurrencyFormatter implements ValueFormatter {

    @Override
    public boolean supports(String type) {
        return "currency".equalsIgnoreCase(type);
    }

    @Override
    public Object format(Object value, FormatConfig config) {
        var locale = Locale.forLanguageTag(config.locale().replace("_", "-"));
        var formatter = NumberFormat.getCurrencyInstance(locale);
        formatter.setCurrency(Currency.getInstance(config.currency()));
        formatter.setMinimumFractionDigits(config.fraction());
        formatter.setMaximumFractionDigits(config.fraction());
        return formatter.format(value);
    }
}
