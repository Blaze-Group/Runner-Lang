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

public class STD implements Module {
    @Override
    public void init() {
        MapValue SystemMap = new MapValue(5);
        MapValue ConsoleMap = new MapValue(5);
        MapValue ThreadMap = new MapValue(5);

        ConsoleMap.set("Echo", new Echo());
        SystemMap.set("Console", ConsoleMap);
        Variables.define("System", SystemMap);
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
}
