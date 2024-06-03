package com.blaze.runner.Modules.GUI;

import com.blaze.runner.Libary.Converters;
import com.blaze.runner.Libary.Function;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Values.StringValue;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JFrameValue extends WindowValue {

    final JFrame frame;

    public JFrameValue(JFrame frame) {
        super(9, frame);
        this.frame = frame;
        init();
    }

    private void init() {
        set("getTitle", Converters.voidToString(frame::getTitle));
        set("getResizable", Converters.voidToBoolean(frame::isResizable));
        set("getDefaultCloseOperation", Converters.voidToInt(frame::getDefaultCloseOperation));
        set("setDefaultCloseOperation", Converters.intToVoid(frame::setDefaultCloseOperation));
        set("setResizable", Converters.booleanOptToVoid(frame::setResizable));
        set("setTitle", Converters.stringToVoid(frame::setTitle));
        set("setIcon", new setIcon());
    }

    private class setIcon implements Function {

        @Override
        public Value execute(Value... args) {
            String path_to_icon = args[0].asString();
            ImageIcon icon = new ImageIcon(path_to_icon);
            frame.setIconImage(icon.getImage());
            return NumberValue.ZERO;
        }
    }
}