package com.blaze.runner.Parser.Visitors;

import com.blaze.runner.Parser.AST.IncludeStatement;
import com.blaze.runner.Parser.AST.Statement;
import com.blaze.runner.Parser.AST.Visitor;
import com.blaze.runner.Parser.Parser.AbstractVisitor;
import com.blaze.runner.Parser.Parser.VisitorUtils;

public abstract class LintVisitor extends AbstractVisitor {

    protected void applyVisitor(IncludeStatement s, Visitor visitor) {
        final Statement program = VisitorUtils.includeProgram(s);
        if (program != null) {
            program.accept(visitor);
        }
    }
}
