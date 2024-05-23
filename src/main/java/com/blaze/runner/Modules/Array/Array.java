package com.blaze.runner.Modules.Array;
import com.blaze.runner.Exceptions.*;
import com.blaze.runner.Libary.*;

import com.blaze.runner.Modules.Module;
import com.blaze.runner.Runtime.*;
import com.blaze.runner.Runtime.Values.*;

import java.util.Arrays;


public final class Array implements Module {


    @Override
    public void init() {
        MapValue ArrayMap = new MapValue(10);
        ArrayMap.set("Array", new ArrayNewarr());
        ArrayMap.set("Join", new Function() {
            @Override
            public Value execute(Value... args) {
                Arguments.checkRange(1, 4, args.length);
                if (args[0].type() != Types.ARRAY) {
                    throw new TypeException("Array expected in first argument");
                }

                final ArrayValue array = (ArrayValue) args[0];
                switch (args.length) {
                    case 1:
                        return ArrayValue.joinToString(array, "", "", "");
                    case 2:
                        return ArrayValue.joinToString(array, args[1].asString(), "", "");
                    case 3:
                        return ArrayValue.joinToString(array, args[1].asString(), args[2].asString(), args[2].asString());
                    case 4:
                        return ArrayValue.joinToString(array, args[1].asString(), args[2].asString(), args[3].asString());
                    default:
                        throw new AutoException("Wrong number of arguments");
                }
            }
        });
        ArrayMap.set("Sort", new Function() {
            @Override
            public Value execute(Value... args) {
                Arguments.checkAtLeast(1, args.length);
                if (args[0].type() != Types.ARRAY) {
                    throw new TypeException("Array expected in first argument");
                }
                final Value[] elements = ((ArrayValue) args[0]).getCopyElements();

                switch (args.length) {
                    case 1:
                        Arrays.sort(elements);
                        break;
                    case 2:
                        final Function comparator = ValueUtils.consumeFunction(args[1], 1);
                        Arrays.sort(elements, (o1, o2) -> comparator.execute(o1, o2).asInt());
                        break;
                    default:
                        throw new AutoException("Wrong number of arguments");
                }

                return new ArrayValue(elements);
            }
        });
        ArrayMap.set("Lenght", new Function() {
            @Override
            public Value execute(Value... args) {
                Arguments.check(1, args.length);

                final Value val = args[0];
                final int length;
                switch (val.type()) {
                    case Types.ARRAY:
                        length = ((ArrayValue) val).size();
                        break;
                    case Types.MAP:
                        length = ((MapValue) val).size();
                        break;
                    case Types.STRING:
                        length = ((StringValue) val).length();
                        break;
                    case Types.FUNCTION:
                        final Function func = ((FunctionValue) val).getValue();
                        if (func instanceof UserDefinedFunction) {
                            length = ((UserDefinedFunction) func).getArgsCount();
                        } else {
                            length = 0;
                        }
                        break;
                    default:
                        length = 0;

                }
                return NumberValue.of(length);
            }
        });
        Variables.define("Array", ArrayMap);
    }

    private static class ArrayNewarr implements Function {

        @Override
        public Value execute(Value... args) {
            return createArray(args, 0);
        }

        private ArrayValue createArray(Value[] args, int index) {
            final int size = args[index].asInt();
            final int last = args.length - 1;
            ArrayValue array = new ArrayValue(size);
            if (index == last) {
                for (int i = 0; i < size; i++) {
                    array.set(i, NumberValue.ZERO);
                }
            } else if (index < last) {
                for (int i = 0; i < size; i++) {
                    array.set(i, createArray(args, index + 1));
                }
            }
            return array;
        }
    }
}
