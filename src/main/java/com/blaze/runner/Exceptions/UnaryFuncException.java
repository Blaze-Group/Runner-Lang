package com.blaze.runner.Exceptions;

public final class UnaryFuncException extends RuntimeException {
    
    private final String functionName;

    public UnaryFuncException(String name) {
        super("Unknown function " + name);
        this.functionName = name;
    }

    public String getFunctionName() {
        return functionName;
    }
}
