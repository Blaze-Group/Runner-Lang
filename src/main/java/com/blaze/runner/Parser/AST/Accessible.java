package com.blaze.runner.Parser.AST;

import com.blaze.runner.Runtime.Value;

public interface Accessible extends Node {

    Value get();
    
    Value set(Value value);
}
