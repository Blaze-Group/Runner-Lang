package com.blaze.runner.Modules.GUI;

import com.blaze.runner.Libary.Function;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Runtime.Value;

import javax.swing.*;

public class Dialog implements Function {

    @Override
    public Value execute(Value... args) {
        final String v = JOptionPane.showInputDialog(args[0].asString());
        return new StringValue(v == null ? "0" : v);
    }
}
