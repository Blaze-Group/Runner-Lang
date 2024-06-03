package com.blaze.runner.Runtime;

public interface SourceLocation {

    default Range getRange() {
        return null;
    }
}
