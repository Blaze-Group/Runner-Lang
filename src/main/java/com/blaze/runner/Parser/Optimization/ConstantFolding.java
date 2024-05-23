package com.blaze.runner.Parser.Optimization;

import com.blaze.runner.Exceptions.OperatorException;
import com.blaze.runner.Parser.AST.BinaryExpression;
import com.blaze.runner.Parser.AST.ConditionalExpression;
import com.blaze.runner.Parser.AST.FunctionDefineStatement;
import com.blaze.runner.Parser.AST.Node;
import com.blaze.runner.Parser.AST.UnaryExpression;
import com.blaze.runner.Parser.AST.ValueExpression;
import com.blaze.runner.Parser.Parser.VisitorUtils;

import java.util.HashSet;
import java.util.Set;


public class ConstantFolding extends OptimizationVisitor<Void> implements Optimizable {

    private static final Set<String> OPERATORS = VisitorUtils.operators();

    private int binaryExpressionFoldingCount;
    private int conditionalExpressionFoldingCount;
    private int unaryExpressionFoldingCount;

    private final Set<String> overloadedOperators;

    public ConstantFolding() {
        overloadedOperators = new HashSet<>();
    }

    @Override
    public Node optimize(Node node) {
        return node.accept(this, null);
    }

    @Override
    public int optimizationsCount() {
        return binaryExpressionFoldingCount
                + conditionalExpressionFoldingCount
                + unaryExpressionFoldingCount;
    }

    @Override
    public String summaryInfo() {
        if (optimizationsCount() == 0) return "";
        final StringBuilder sb = new StringBuilder();
        if (binaryExpressionFoldingCount > 0) {
            sb.append("\nBinaryExpression foldings: ").append(binaryExpressionFoldingCount);
        }
        if (conditionalExpressionFoldingCount > 0) {
            sb.append("\nConditionalExpression foldings: ").append(conditionalExpressionFoldingCount);
        }
        if (unaryExpressionFoldingCount > 0) {
            sb.append("\nUnaryExpression foldings: ").append(unaryExpressionFoldingCount);
        }
        return sb.toString();
    }

    @Override
    public Node visit(BinaryExpression s, Void t) {
        if (overloadedOperators.contains(s.operation.toString())) {
            return super.visit(s, t);
        }
        if ( (s.expr1 instanceof ValueExpression) && (s.expr2 instanceof ValueExpression) ) {
            binaryExpressionFoldingCount++;
            try {
                return new ValueExpression(s.eval());
            } catch (OperatorException op) {
                binaryExpressionFoldingCount--;
            }
        }
        return super.visit(s, t);
    }

    @Override
    public Node visit(ConditionalExpression s, Void t) {
        if (overloadedOperators.contains(s.operation.getName())) {
            return super.visit(s, t);
        }
        if ( (s.expr1 instanceof ValueExpression) && (s.expr2 instanceof ValueExpression) ) {
            conditionalExpressionFoldingCount++;
            try {
                return new ValueExpression(s.eval());
            } catch (OperatorException op) {
                conditionalExpressionFoldingCount--;
            }
        }
        return super.visit(s, t);
    }

    @Override
    public Node visit(UnaryExpression s, Void t) {
        if (overloadedOperators.contains(s.operation.toString())) {
            return super.visit(s, t);
        }
        if (s.expr1 instanceof ValueExpression) {
            unaryExpressionFoldingCount++;
            try {
                return new ValueExpression(s.eval());
            } catch (OperatorException op) {
                unaryExpressionFoldingCount--;
            }
        }
        return super.visit(s, t);
    }

    @Override
    public Node visit(FunctionDefineStatement s, Void t) {
        if (OPERATORS.contains(s.name)) {
            overloadedOperators.add(s.name);
        }
        return super.visit(s, t);
    }
}
