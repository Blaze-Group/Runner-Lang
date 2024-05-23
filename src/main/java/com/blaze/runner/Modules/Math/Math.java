package com.blaze.runner.Modules.Math;
import com.blaze.runner.Libary.*;
import com.blaze.runner.Modules.Module;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Variables;

import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;



public final class Math implements Module {

    private static final DoubleFunction<NumberValue> doubleToNumber = NumberValue::of;

    public static void initConstants() {
        Variables.define("PI", NumberValue.of(java.lang.Math.PI));

    }

    @Override
    public void init() {
        initConstants();
        Functions.set("abs", Math::abs);
        Functions.set("cos", functionConvert(java.lang.Math::cos));
        Functions.set("log", functionConvert(java.lang.Math::log));
        Functions.set("sin", functionConvert(java.lang.Math::sin));
        Functions.set("sqrt", functionConvert(java.lang.Math::sqrt));
        Functions.set("tan", functionConvert(java.lang.Math::tan));
    }

    private static Value abs(Value... args) {
        Arguments.check(1, args.length);
        final Object raw = args[0].raw();
        if (raw instanceof Double) {
            return NumberValue.of(java.lang.Math.abs((double) raw));
        }
        if (raw instanceof Float) {
            return NumberValue.of(java.lang.Math.abs((float) raw));
        }
        if (raw instanceof Long) {
            return NumberValue.of(java.lang.Math.abs((long) raw));
        }
        return NumberValue.of(java.lang.Math.abs(args[0].asInt()));
    }




    private static Function functionConvert(DoubleUnaryOperator op) {
        return args -> {
            Arguments.check(1, args.length);
            return doubleToNumber.apply(op.applyAsDouble(args[0].asNumber()));
        };
    }

}
