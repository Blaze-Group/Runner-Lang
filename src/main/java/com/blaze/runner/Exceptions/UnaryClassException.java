package com.blaze.runner.Exceptions;

public final class UnaryClassException extends RuntimeException {
    
    private final String className;

    public UnaryClassException(String name) {
        super("Unknown class " + name);
        this.className = name;
    }

    public String getClassName() {
        return className;
    }
}
