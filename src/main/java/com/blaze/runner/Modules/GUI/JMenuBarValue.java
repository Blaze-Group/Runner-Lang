package com.blaze.runner.Modules.GUI;

import com.blaze.runner.Libary.Function;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.FunctionValue;
import com.blaze.runner.Runtime.Values.NumberValue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JMenuBarValue extends JComponentValue{
    JMenuItem[] items;
    final JMenuBar bar;

    public JMenuBarValue(JMenuBar bar) {
        super(1, bar);
        this.bar = bar;
        init();
    }

    private void init() {
        set("add", new FunctionValue(this::add));
        set("setAction", new FunctionValue(this::setAction));
    }

    private Value add(Value... args) {
        JMenu menu = new JMenu(args[0].asString());
        bar.add(menu);
        items = new JMenuItem[10];

        for(int i = 1; i < args.length; i++){
            items[i] = new JMenuItem(args[i].asString());
            menu.add(items[i]);
        }

        return NumberValue.ZERO;
    }

    private Value setAction(Value... args){
        Function body;
        body = ((FunctionValue) args[1]).getValue();
        ActionListener enableActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                body.execute();
            }
        };
        items[args[0].asInt()].addActionListener(enableActionListener);


        return NumberValue.ZERO;
    }
}
