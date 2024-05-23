package com.blaze.runner.Runtime;

import com.blaze.runner.Parser.AST.Arguments;
import com.blaze.runner.Parser.AST.Statement;
import com.blaze.runner.Runtime.Values.ClassInstanceValue;
import com.blaze.runner.Runtime.Values.UserDefinedFunction;

public class ClassMethod extends UserDefinedFunction {
    
    public final ClassInstanceValue classInstance;
    
    public ClassMethod(Arguments arguments, Statement body, ClassInstanceValue classInstance) {
        super(arguments, body);
        this.classInstance = classInstance;
    }
    
    @Override
    public Value execute(Value[] values) {
        Variables.push();
        Variables.define("this", classInstance.getThisMap());
        
        try {
            return super.execute(values);
        } finally {
            Variables.pop();
        }
    }
}
