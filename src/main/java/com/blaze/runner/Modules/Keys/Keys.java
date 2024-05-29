package com.blaze.runner.Modules.Keys;

import com.blaze.runner.Modules.Module;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Variables;

import java.awt.event.KeyEvent;

public class Keys implements Module {
    @Override
    public void init() {
        MapValue keys = new MapValue(100);
        keys.set("UP", new NumberValue(KeyEvent.VK_UP));
        keys.set("DOWN", new NumberValue(KeyEvent.VK_DOWN));
        keys.set("LEFT", new NumberValue(KeyEvent.VK_LEFT));
        keys.set("RIGHT", new NumberValue(KeyEvent.VK_RIGHT));

        keys.set("0", new NumberValue(KeyEvent.VK_0));
        keys.set("1", new NumberValue(KeyEvent.VK_1));
        keys.set("2", new NumberValue(KeyEvent.VK_2));
        keys.set("3", new NumberValue(KeyEvent.VK_3));
        keys.set("4", new NumberValue(KeyEvent.VK_4));
        keys.set("5", new NumberValue(KeyEvent.VK_5));
        keys.set("6", new NumberValue(KeyEvent.VK_6));
        keys.set("7", new NumberValue(KeyEvent.VK_7));
        keys.set("8", new NumberValue(KeyEvent.VK_8));
        keys.set("9", new NumberValue(KeyEvent.VK_9));

        keys.set("A", new NumberValue(KeyEvent.VK_A));
        keys.set("B", new NumberValue(KeyEvent.VK_B));
        keys.set("C", new NumberValue(KeyEvent.VK_C));
        keys.set("D", new NumberValue(KeyEvent.VK_D));
        keys.set("E", new NumberValue(KeyEvent.VK_E));
        keys.set("F", new NumberValue(KeyEvent.VK_F));
        keys.set("G", new NumberValue(KeyEvent.VK_G));
        keys.set("H", new NumberValue(KeyEvent.VK_H));
        keys.set("I", new NumberValue(KeyEvent.VK_I));
        keys.set("J", new NumberValue(KeyEvent.VK_J));
        keys.set("K", new NumberValue(KeyEvent.VK_K));
        keys.set("L", new NumberValue(KeyEvent.VK_L));
        keys.set("M", new NumberValue(KeyEvent.VK_M));
        keys.set("N", new NumberValue(KeyEvent.VK_N));
        keys.set("O", new NumberValue(KeyEvent.VK_O));
        keys.set("P", new NumberValue(KeyEvent.VK_P));
        keys.set("Q", new NumberValue(KeyEvent.VK_Q));
        keys.set("R", new NumberValue(KeyEvent.VK_R));
        keys.set("S", new NumberValue(KeyEvent.VK_S));
        keys.set("T", new NumberValue(KeyEvent.VK_T));
        keys.set("U", new NumberValue(KeyEvent.VK_U));
        keys.set("V", new NumberValue(KeyEvent.VK_V));
        keys.set("W", new NumberValue(KeyEvent.VK_W));
        keys.set("X", new NumberValue(KeyEvent.VK_X));
        keys.set("Y", new NumberValue(KeyEvent.VK_Y));
        keys.set("Z", new NumberValue(KeyEvent.VK_Z));

        keys.set("TAB", new NumberValue(KeyEvent.VK_TAB));
        keys.set("ALT", new NumberValue(KeyEvent.VK_ALT));
        keys.set("SHIFT", new NumberValue(KeyEvent.VK_SHIFT));
        keys.set("CAPS_LOCK", new NumberValue(KeyEvent.VK_CAPS_LOCK));
        keys.set("CONTROL", new NumberValue(KeyEvent.VK_CONTROL));
        keys.set("ENTER", new NumberValue(KeyEvent.VK_ENTER));
        keys.set("ESCAPE", new NumberValue(KeyEvent.VK_ESCAPE));
        keys.set("WINDOWS", new NumberValue(KeyEvent.VK_WINDOWS));

        keys.set("F1", new NumberValue(KeyEvent.VK_F1));
        keys.set("F2", new NumberValue(KeyEvent.VK_F2));
        keys.set("F3", new NumberValue(KeyEvent.VK_F3));
        keys.set("F4", new NumberValue(KeyEvent.VK_F4));
        keys.set("F5", new NumberValue(KeyEvent.VK_F5));
        keys.set("F6", new NumberValue(KeyEvent.VK_F6));
        keys.set("F7", new NumberValue(KeyEvent.VK_F7));
        keys.set("F8", new NumberValue(KeyEvent.VK_F8));
        keys.set("F9", new NumberValue(KeyEvent.VK_F9));
        keys.set("F10", new NumberValue(KeyEvent.VK_F10));
        keys.set("F11", new NumberValue(KeyEvent.VK_F11));
        keys.set("F12", new NumberValue(KeyEvent.VK_F12));
        Variables.define("Keys", keys);
    }
}
