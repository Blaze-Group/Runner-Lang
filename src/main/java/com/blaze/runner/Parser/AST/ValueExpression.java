package com.blaze.runner.Parser.AST;

import com.blaze.runner.Libary.Function;
import com.blaze.runner.Runtime.Values.FunctionValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Libary.Types;
import com.blaze.runner.Runtime.Value;


public final class ValueExpression extends InterruptableNode implements Expression {
    
    public final Value value;
    
    public ValueExpression(Number value) {
        this.value = NumberValue.of(value);
    }
    
    public ValueExpression(String value) {
        this.value = new StringValue(value);
    }
    
    public ValueExpression(Function value) {
        this.value = new FunctionValue(value);
    }
    
    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value eval() {
        super.interruptionCheck();
        return value;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <R, T> R accept(ResultVisitor<R, T> visitor, T t) {
        return visitor.visit(this, t);
    }

    @Override
    public String toString() {
        if (value.type() == Types.STRING) {
            return "\"" + value.asString() + "\"";
        }
        return value.toString();
    }
}
