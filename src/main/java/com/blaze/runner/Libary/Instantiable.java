package com.blaze.runner.Libary;


import com.blaze.runner.Runtime.Value;

public interface Instantiable {
    
    Value newInstance(Value[] args);
}
