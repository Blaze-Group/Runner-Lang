package com.blaze.runner.Exceptions;

public final class UnaryPropertyException extends RuntimeException {

    private final String propertyName;

    public UnaryPropertyException(String name) {
        super("Unknown property " + name);
        this.propertyName = name;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
