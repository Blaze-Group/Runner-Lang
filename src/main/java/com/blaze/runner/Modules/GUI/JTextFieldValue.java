package com.blaze.runner.Modules.GUI;


import com.blaze.runner.Libary.*;
import com.blaze.runner.Runtime.Values.FunctionValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Value;

import javax.swing.JTextField;
import static com.blaze.runner.Libary.ValueUtils.consumeFunction;

public class JTextFieldValue extends JTextComponentValue {

    private final JTextField textField;

    public JTextFieldValue(JTextField textField) {
        super(10, textField);
        this.textField = textField;
        init();
    }

    private void init() {
        set("onAction", new FunctionValue(this::addActionListener));
        set("addActionListener", new FunctionValue(this::addActionListener));
        set("getColumns", Converters.voidToInt(textField::getColumns));
        set("getHorizontalAlignment", Converters.voidToInt(textField::getHorizontalAlignment));
        set("getScrollOffset", Converters.voidToInt(textField::getScrollOffset));
        set("postActionEvent", Converters.voidToVoid(textField::postActionEvent));
        set("setActionCommand", Converters.stringToVoid(textField::setActionCommand));
        set("setColumns", Converters.intToVoid(textField::setColumns));
        set("setHorizontalAlignment", Converters.intToVoid(textField::setHorizontalAlignment));
        set("setScrollOffset", Converters.intToVoid(textField::setScrollOffset));
    }

    private Value addActionListener(Value... args) {
        Arguments.check(1, args.length);
        Function action = ValueUtils.consumeFunction(args[0], 1);
        textField.addActionListener(e -> action.execute());
        return NumberValue.ZERO;
    }
}