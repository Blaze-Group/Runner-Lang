package com.blaze.runner.Parser.Parser;

import com.blaze.runner.Parser.AST.ArrayExpression;
import com.blaze.runner.Parser.AST.Expression;
import com.blaze.runner.Parser.AST.Statement;
import com.blaze.runner.Parser.AST.UsingStatement;
import com.blaze.runner.Parser.AST.ValueExpression;

import java.util.HashSet;
import java.util.Set;

public class ModuleDetector extends AbstractVisitor {

    private Set<String> modules;

    public ModuleDetector() {
        modules = new HashSet<>();
    }

    public Set<String> detect(Statement s) {
        s.accept(this);
        return modules;
    }

    @Override
    public void visit(UsingStatement st) {
        if (st.expression instanceof ArrayExpression) {
            ArrayExpression ae = (ArrayExpression) st.expression;
            for (Expression expr : ae.elements) {
                modules.add(expr.eval().asString());
            }
        }
        if (st.expression instanceof ValueExpression) {
            ValueExpression ve = (ValueExpression) st.expression;
            modules.add(ve.value.asString());
        }
        super.visit(st);
    }
}
