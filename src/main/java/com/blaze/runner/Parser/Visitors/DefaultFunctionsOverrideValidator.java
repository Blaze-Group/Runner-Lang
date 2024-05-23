package com.blaze.runner.Parser.Visitors;

import com.blaze.runner.Parser.Parser.Console;
import com.blaze.runner.Libary.Functions;
import com.blaze.runner.Parser.AST.FunctionDefineStatement;
import com.blaze.runner.Parser.AST.IncludeStatement;
import com.blaze.runner.Parser.AST.UsingStatement;

public final class DefaultFunctionsOverrideValidator extends LintVisitor {

    @Override
    public void visit(FunctionDefineStatement s) {
        super.visit(s);
        if (Functions.isExists(s.name)) {
            Console.error(String.format(
                    "Warning: function \"%s\" overrides default module function", s.name));
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
