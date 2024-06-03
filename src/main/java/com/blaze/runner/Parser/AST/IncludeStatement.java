package com.blaze.runner.Parser.AST;

import com.blaze.runner.Exceptions.ParseException;
import com.blaze.runner.Parser.Parser.Lexer;
import com.blaze.runner.Parser.Parser.Parser;
import com.blaze.runner.Parser.Parser.SourceLoader;
import com.blaze.runner.Parser.Parser.Token;
import com.blaze.runner.Parser.Parser.FunctionAdder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public final class IncludeStatement extends InterruptableNode implements Statement {

    public final Expression expression;
    
    public IncludeStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {
        super.interruptionCheck();
        String path = expression.eval().asString();
        if (!Files.exists(Path.of(path))) {
            path = "Lib\\User Module\\" + expression.eval().asString() + ".rsc";
            if(!Files.exists(Path.of(path))) {
                path = "Lib\\" + expression.eval().asString() + ".rsc";
            }
        }
        else {
            // None
        }

        try {
            final Statement program = loadProgram(path + ".rsc");
            if (program != null) {
                program.accept(new FunctionAdder());
                program.execute();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Statement loadProgram(String path) throws IOException {
        final String input = SourceLoader.readSource(path);
        final List<Token> tokens = Lexer.tokenize(input);
        final Parser parser = new Parser(tokens);
        final Statement program = parser.parse();
        if (parser.getParseErrors().hasErrors()) {
            throw new ParseException(parser.getParseErrors().toString());
        }
        return program;
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
        return "include " + expression;
    }
}
