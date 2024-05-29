package com.blaze.runner.Modules.Keyboard;

import com.blaze.runner.Libary.Arguments;
import com.blaze.runner.Libary.Function;
import com.blaze.runner.Modules.Module;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Runtime.Variables;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Keyboard implements Module {

    public static Robot keyboard;

    public static final int CLICK_DELAY = 200;
    public static final int TYPING_DELAY = 50;
    public static final Map<Character, Integer> SYMBOL_CODES;
    static {
        SYMBOL_CODES = new HashMap<>(10);
        SYMBOL_CODES.put('_', KeyEvent.VK_MINUS);
        SYMBOL_CODES.put(':', KeyEvent.VK_SEMICOLON);
    }

    @Override
    public void init() {
        try {
            keyboard = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

        MapValue KeyboardMap = new MapValue(5);
        KeyboardMap.set("Press", new KeyPress());
        KeyboardMap.set("Release", new KeyRelease());
        KeyboardMap.set("Type", new Type());

        Variables.define("Keyboard", KeyboardMap);
    }

    public static class KeyPress implements Function {

        @Override
        public Value execute(Value... args) {
            int keyCode = args[0].asInt();
            keyboard.keyPress(keyCode);
            return NumberValue.ZERO;
        }
    }

    public static class Type implements Function {

        @Override
        public Value execute(Value... args) {
            Arguments.check(1, args.length);
            try {
                typeText(args[0].asString());
            } catch (IllegalArgumentException iae) {}
            return NumberValue.ZERO;
        }
    }

    private static synchronized void typeText(String text) {
        for (char ch : text.toCharArray()) {
            typeSymbol(ch);
        }
    }

    private static void typeSymbol(char ch) {
        int code = KeyEvent.getExtendedKeyCodeForChar(ch);

        boolean isUpperCase = Character.isLetter(ch) && Character.isUpperCase(ch);
        boolean needPressShift = isUpperCase;
        if (!isUpperCase) {
            final int symbolIndex = "!@#$%^&*()".indexOf(ch);
            if (symbolIndex != -1) {
                needPressShift = true;
                code = '1' + symbolIndex;
            } else if (SYMBOL_CODES.containsKey(ch)) {
                needPressShift = true;
                code = SYMBOL_CODES.get(ch);
            }
        }

        if (code == KeyEvent.VK_UNDEFINED) return;

        if (needPressShift) {
            // press shift
            keyboard.keyPress(KeyEvent.VK_SHIFT);
            keyboard.delay(TYPING_DELAY);
        }

        keyboard.keyPress(code);
        keyboard.delay(TYPING_DELAY);
        keyboard.keyRelease(code);

        if (needPressShift) {
            // release shift
            keyboard.delay(TYPING_DELAY);
            keyboard.keyRelease(KeyEvent.VK_SHIFT);
            keyboard.delay(TYPING_DELAY);
        }
    }

    public static class KeyRelease implements Function {

        @Override
        public Value execute(Value... args) {
            int keyCode = args[0].asInt();
            keyboard.keyRelease(keyCode);
            return NumberValue.ZERO;
        }
    }
}
