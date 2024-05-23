package com.blaze.runner.Libary;


import com.blaze.runner.Runtime.Value;

import java.net.UnknownHostException;

public interface Function {

    Value execute(Value... args);
}
