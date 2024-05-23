package com.blaze.runner.Exceptions;

public final class OperatorException extends RuntimeException {

    public OperatorException(Object operation) {
        super("Operation " + operation + " is not supported");
    }
    
    public OperatorException(Object operation, String message) {
        super("Operation " + operation + " is not supported " + message);
    }
}
