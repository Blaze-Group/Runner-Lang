package com.blaze.runner.Parser.Parser;

import com.blaze.runner.Parser.AST.FunctionDefineStatement;


public final class FunctionAdder extends AbstractVisitor {

    @Override
    public void visit(FunctionDefineStatement s) {
        super.visit(s);
        s.execute();
    }
}
