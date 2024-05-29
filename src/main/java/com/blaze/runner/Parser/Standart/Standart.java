package com.blaze.runner.Parser.Standart;

import com.blaze.runner.Libary.Arguments;
import com.blaze.runner.Libary.Function;
import com.blaze.runner.Libary.Types;
import com.blaze.runner.Libary.ValueUtils;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.FunctionValue;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Runtime.Variables;

import java.util.Scanner;

public class Standart {
    public static class Input implements Function {

        @Override
        public Value execute(Value... args) {
            System.out.print(args[0].asString());
            Scanner sc = new Scanner(System.in);
            return new StringValue(sc.next());
        }
    }

    public static class InputLine implements Function {

        @Override
        public Value execute(Value... args) {
            System.out.println(args[0].asString());
            Scanner sc = new Scanner(System.in);
            return new StringValue(sc.nextLine());
        }
    }

    public final static class tryCatch implements Function{

        @Override
        public Value execute(Value... args) {
            Arguments.checkOrOr(1,2,args.length);
            try{
                return ValueUtils.consumeFunction(args[0],0).execute();
            }catch(Exception exception){
                if(args.length == 2){
                    switch(args[1].type()){
                        case Types.FUNCTION -> {
                            final String message = exception.getMessage();
                            final Function catchfn = ((FunctionValue)args[1]).getValue();
                            return catchfn.execute(new StringValue(exception.getClass().getName()), new StringValue(message == null ? "" : message));
                        }default -> {
                            return args[1];
                        }
                    }

                }
            }
            return NumberValue.ZERO;
        }
    }

    public static class Exit implements Function {

        @Override
        public Value execute(Value... args) {
            Arguments.check(1, args.length);
            System.exit(args[0].asInt());
            return NumberValue.ZERO;
        }
    }

    public static class Replacement implements Function {

        @Override
        public Value execute(Value... args) {
            String variable_name = args[0].asString();
            String replace_char = args[1].asString();
            String replace_to_char = args[2].asString();
            variable_name = variable_name.replace(replace_char, replace_to_char);
            return new StringValue(variable_name);
        }
    }
}
