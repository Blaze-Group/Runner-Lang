package com.blaze.runner.Modules.DateTime;


import com.blaze.runner.Libary.Arguments;
import com.blaze.runner.Libary.Function;
import com.blaze.runner.Modules.Module;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Runtime.Variables;

import java.util.Date;


public final class DateTime implements Module {



    @Override
    public void init() {
        MapValue TimeMap = new MapValue(5);
        TimeMap.set("Now", new Function() {
            @Override
            public Value execute(Value... args) {
                Date date = new Date();
                return new StringValue(date.toString());
            }
        });
        TimeMap.set("Wait", new Function() {
            @Override
            public Value execute(Value... args) {
                Arguments.check(1, args.length);

                try {
                    Thread.sleep((long) args[0].asNumber());
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                return NumberValue.ZERO;
            }
        });

        Variables.define("Time", TimeMap);

    }
}
