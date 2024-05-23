package com.blaze.runner.Parser.Parser;

import com.blaze.runner.Parser.AST.AssignmentExpression;
import com.blaze.runner.Parser.AST.VariableExpression;


public final class VariablePrinter extends AbstractVisitor {

    @Override
    public void visit(AssignmentExpression s) {
        super.visit(s);
        Console.println(s.target);
    }

    @Override
    public void visit(VariableExpression s) {
        super.visit(s);
        Console.println(s.name);
    }
}
