package com.blaze.runner.Parser.AST;

import com.blaze.runner.Runtime.Value;


public interface Expression extends Node {
    
    Value eval();
}
