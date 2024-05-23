package com.blaze.runner.Modules.Random;


import com.blaze.runner.Libary.Arguments;
import com.blaze.runner.Libary.Function;
import com.blaze.runner.Modules.Module;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Variables;


public final class Random implements Module {

    private static final java.util.Random RND = new java.util.Random();

    @Override
    public void init() {
        MapValue RandomMap = new MapValue(5);
        RandomMap.set("Rand", new Function() {
            @Override
            public Value execute(Value... args) {
                Arguments.checkRange(0, 2, args.length);
                if (args.length == 0) return NumberValue.of(RND.nextDouble());

                final Object raw = args[0].raw();
                if (raw instanceof Long) {
                    long from = 0L;
                    long to = 100L;
                    if (args.length == 1) {
                        to = (long) raw;
                    } else if (args.length == 2) {
                        from = (long) raw;
                        to = ((NumberValue) args[1]).asLong();
                    }
                    final long randomLong = RND.nextLong() >>> 1;
                    return NumberValue.of(randomLong % (to - from) + from);
                }

                int from = 0;
                int to = 100;
                if (args.length == 1) {
                    to = args[0].asInt();
                } else if (args.length == 2) {
                    from = args[0].asInt();
                    to = args[1].asInt();
                }
                return NumberValue.of(RND.nextInt(to - from) + from);
            }
        });

        Variables.define("Random", RandomMap);

    }
}
