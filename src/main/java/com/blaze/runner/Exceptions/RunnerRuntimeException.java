package com.blaze.runner.Exceptions;

import com.blaze.runner.Runtime.Range;
import com.blaze.runner.Runtime.SourceLocatedError;

public class RunnerRuntimeException extends RuntimeException implements SourceLocatedError {

    private final Range range;

    public RunnerRuntimeException() {
        super();
        this.range = null;
    }

    public RunnerRuntimeException(Exception ex) {
        super(ex);
        this.range = null;
    }

    public RunnerRuntimeException(String message) {
        this(message, (Range) null);
    }

    public RunnerRuntimeException(String message, Range range) {
        super(message);
        this.range = range;
    }

    public RunnerRuntimeException(String message, Throwable ex) {
        super(message, ex);
        this.range = null;
    }

    @Override
    public Range getRange() {
        return range;
    }
}
