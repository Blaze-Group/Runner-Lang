package com.blaze.runner.Exceptions;

public class RNException extends RuntimeException {
    private final String type;
    private final String text;

    public RNException(String type, String text) {
        super();
        this.type = type;
        this.text = "\u001b[31m" + text;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
