package com.blaze.runner.Parser.Parser;

import com.blaze.runner.Parser.Visitors.AssignValidator;
import com.blaze.runner.Parser.Visitors.UsingWithNonStringValueValidator;
import com.blaze.runner.Parser.Visitors.DefaultFunctionsOverrideValidator;
import com.blaze.runner.Libary.Functions;
import com.blaze.runner.Runtime.Variables;
import com.blaze.runner.Parser.AST.Statement;
import com.blaze.runner.Parser.AST.Visitor;

public final class Linter {

    public static void lint(Statement program) {
        new Linter(program).execute();
    }

    private final Statement program;

    private Linter(Statement program) {
        this.program = program;
    }
    
    public void execute() {
        final Visitor[] validators = new Visitor[] {
            new UsingWithNonStringValueValidator(),
            new AssignValidator(),
            new DefaultFunctionsOverrideValidator()
        };
        resetState();
        for (Visitor validator : validators) {
            program.accept(validator);
            resetState();
        }
        Console.println("Lint validation complete!");
    }

    private void resetState() {
        Variables.clear();
        Functions.getFunctions().clear();
    }
}
