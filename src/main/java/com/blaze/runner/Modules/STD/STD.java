package com.blaze.runner.Modules.STD;

import com.blaze.runner.Libary.Arguments;
import com.blaze.runner.Libary.Function;
import com.blaze.runner.Libary.Functions;
import com.blaze.runner.Libary.Types;
import com.blaze.runner.Modules.Module;
import com.blaze.runner.Parser.Parser.Console;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.FunctionValue;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Variables;

import java.util.Random;

public class STD implements Module {

    private static final Random RND = new Random();

    @Override
    public void init() {
        MapValue ConsoleMap = new MapValue(1);
        MapValue ThreadMap = new MapValue(2);

        Functions.set("Random", new Randomize());
        ThreadMap.set("create", new thread());
        ConsoleMap.set("Echo", new Echo());

        Variables.define("Console", ConsoleMap);
        Variables.define("Thread", ThreadMap);
    }


    private static class Echo implements Function {

        @Override
        public Value execute(Value... args) {
            final StringBuilder sb = new StringBuilder();
            for (Value arg : args) {
                sb.append(arg.asString());
                sb.append(' ');
            }
            Console.println(sb.toString());
            return NumberValue.ZERO;
        }
    }

    private static class Randomize implements Function {

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
    }

    public final class thread implements Function {

        @Override
        public Value execute(Value... args) {
            Arguments.checkAtLeast(1, args.length);

            Function body;
            if (args[0].type() == Types.FUNCTION) {
                body = ((FunctionValue) args[0]).getValue();
            } else {
                body = Functions.get(args[0].asString());
            }

            final Value[] params = new Value[args.length - 1];
            if (params.length > 0) {
                System.arraycopy(args, 1, params, 0, params.length);
            }

            final Thread thread = new Thread(() -> body.execute(params));
            thread.setUncaughtExceptionHandler(Console::handleException);
            thread.start();
            return NumberValue.ZERO;
        }
    }
}
