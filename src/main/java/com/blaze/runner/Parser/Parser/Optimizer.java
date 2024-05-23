package com.blaze.runner.Parser.Parser;

import com.blaze.runner.Parser.AST.Node;
import com.blaze.runner.Parser.AST.Statement;
import com.blaze.runner.Parser.Optimization.ConstantFolding;
import com.blaze.runner.Parser.Optimization.ConstantPropagation;
import com.blaze.runner.Parser.Optimization.DeadCodeElimination;
import com.blaze.runner.Parser.Optimization.ExpressionSimplification;
import com.blaze.runner.Parser.Optimization.InstructionCombining;
import com.blaze.runner.Parser.Optimization.Optimizable;
import com.blaze.runner.Parser.Optimization.SummaryOptimization;

public final class Optimizer {

    private Optimizer() { }

    public static Statement optimize(Statement statement, int level, boolean showSummary) {
        if (level == 0) return statement;

        final Optimizable optimization = new SummaryOptimization(new Optimizable[] {
            new ConstantFolding(),
            new ConstantPropagation(),
            new DeadCodeElimination(),
            new ExpressionSimplification(),
            new InstructionCombining()
        });

        Node result = statement;
        if (level >= 9) {
            int iteration = 0, lastModifications = 0;
            do {
                lastModifications = optimization.optimizationsCount();
                result = optimization.optimize(result);
                iteration++;
            } while (lastModifications != optimization.optimizationsCount());
            if (showSummary)
                Console.print("Performs " + iteration + " optimization iterations");
        } else {
            for (int i = 0; i < level; i++) {
                result = optimization.optimize(result);
            }
        }
        if (showSummary) {
            Console.println(optimization.summaryInfo());
        }
        return (Statement) result;
    }
}
