package com.blaze.runner.Exceptions;

public final class VariableException extends RuntimeException {
    
    private final String variable;

    public VariableException(String variable) {
        super("Variable " + variable + " does not exists");
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }
}
