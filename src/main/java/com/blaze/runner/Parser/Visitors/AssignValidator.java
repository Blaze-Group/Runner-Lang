package com.blaze.runner.Parser.Visitors;

import com.blaze.runner.Parser.AST.*;
import com.blaze.runner.Parser.Parser.Console;
import com.blaze.runner.Runtime.Variables;


public final class AssignValidator extends LintVisitor {

    @Override
    public void visit(AssignmentExpression s) {
        super.visit(s);
        if (s.target instanceof VariableExpression) {
            final String variable = ((VariableExpression) s.target).name;
            if (Variables.isExists(variable)) {
                Console.error(String.format(
                    "Warning: variable \"%s\" overrides constant", variable));
            }
        }
    }

    @Override
    public void visit(IncludeStatement st) {
        super.visit(st);
        applyVisitor(st, this);
    }

    @Override
    public void visit(UsingStatement st) {
        super.visit(st);
        st.execute();
    }
}
