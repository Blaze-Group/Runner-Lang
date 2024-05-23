package com.blaze.runner.Runtime;

public interface Value extends Comparable<Value> {
    
    Object raw();
    
    int asInt();


    double asNumber();
    
    String asString();
    
    int type();
}
