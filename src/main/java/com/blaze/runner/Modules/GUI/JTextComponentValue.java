package com.blaze.runner.Modules.GUI;


import com.blaze.runner.Libary.*;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Runtime.Value;

import javax.swing.event.CaretEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class JTextComponentValue extends JComponentValue {

    private final JTextComponent textComponent;

    public JTextComponentValue(int functionsCount, JTextComponent textComponent) {
        super(functionsCount + 21, textComponent);
        this.textComponent = textComponent;
        init();
    }

    private void init() {
        set("addCaretListener", this::addCaretListener);
        set("addDocumentListener", this::addDocumentListener);
        set("copy", Converters.voidToVoid(textComponent::copy));
        set("cut", Converters.voidToVoid(textComponent::cut));
        set("getCaretPosition", Converters.voidToInt(textComponent::getCaretPosition));
        set("getDragEnabled", Converters.voidToBoolean(textComponent::getDragEnabled));
        set("getSelectedText", Converters.voidToString(textComponent::getSelectedText));
        set("getSelectionStart", Converters.voidToInt(textComponent::getSelectionStart));
        set("getSelectionEnd", Converters.voidToInt(textComponent::getSelectionEnd));
        set("getText", Converters.voidToString(textComponent::getText));
        set("isEditable", Converters.voidToBoolean(textComponent::isEditable));
        set("moveCaretPosition", Converters.intToVoid(textComponent::moveCaretPosition));
        set("paste", Converters.voidToVoid(textComponent::paste));
        set("replaceSelection", Converters.stringToVoid(textComponent::replaceSelection));
        set("select", Converters.int2ToVoid(textComponent::select));
        set("selectAll", Converters.voidToVoid(textComponent::selectAll));
        set("setCaretPosition", Converters.intToVoid(textComponent::setCaretPosition));
        set("setDragEnabled", Converters.booleanToVoid(textComponent::setDragEnabled));
        set("setEditable", Converters.booleanToVoid(textComponent::setEditable));
        set("setSelectionStart", Converters.intToVoid(textComponent::setSelectionStart));
        set("setSelectionEnd", Converters.intToVoid(textComponent::setSelectionEnd));
        set("setText", Converters.stringToVoid(textComponent::setText));
    }
    
    private Value addCaretListener(Value[] args) {
        Arguments.check(1, args.length);
        final Function action = ValueUtils.consumeFunction(args[0], 0);
        textComponent.addCaretListener((CaretEvent e) -> {
            final MapValue map = new MapValue(2);
            map.set("getDot", NumberValue.of(e.getDot()));
            map.set("getMark", NumberValue.of(e.getMark()));
            action.execute(map);
        });
        return NumberValue.ZERO;
    }
    
    private Value addDocumentListener(Value[] args) {
        Arguments.check(1, args.length);
        final Function action = ValueUtils.consumeFunction(args[0], 0);
        textComponent.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                handleDocumentEvent(DocumentEvent.EventType.INSERT, e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                handleDocumentEvent(DocumentEvent.EventType.REMOVE, e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                handleDocumentEvent(DocumentEvent.EventType.CHANGE, e);
            }
            
            private void handleDocumentEvent(DocumentEvent.EventType type, final DocumentEvent e) {
                final MapValue map = new MapValue(3);
                map.set("getLength", NumberValue.of(e.getLength()));
                map.set("getOffset", NumberValue.of(e.getOffset()));
                map.set("getType", new StringValue(e.getType().toString()));
                action.execute(new StringValue(type.toString()), map);
            }
        });
        return NumberValue.ZERO;
    }
}