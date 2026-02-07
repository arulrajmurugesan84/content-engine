package org.content.domain;

public enum DataType {
    STRING,
    NUMBER;

    public static DataType from(String value) {
        return DataType.valueOf(value.toUpperCase());
    }
}
