package com.blaze.runner.Parser.Optimization;

import com.blaze.runner.Libary.Types;
import com.blaze.runner.Parser.AST.Node;
import com.blaze.runner.Parser.AST.ValueExpression;
import com.blaze.runner.Parser.AST.VariableExpression;
import com.blaze.runner.Runtime.Value;

import java.util.HashMap;
import java.util.Map;


public class ConstantPropagation extends OptimizationVisitor<Map<String, Value>> implements Optimizable {

    private final Map<String, Integer> propagatedVariables;

    public ConstantPropagation() {
        propagatedVariables = new HashMap<>();
    }

    @Override
    public Node optimize(Node node) {
        final Map<String, VariableInfo> variables = new HashMap<>();
        // Find variables
        node.accept(new VariablesGrabber(true), variables);
        // Filter only string/number values with 1 modification
        final Map<String, Value> candidates = new HashMap<>();
        for (Map.Entry<String, VariableInfo> e : variables.entrySet()) {
            final VariableInfo info = e.getValue();
            if (info.modifications != 1) continue;
            if (info.value == null) continue;
            switch (info.value.type()) {
                case Types.NUMBER:
                case Types.STRING:
                    candidates.put(e.getKey(), info.value);
                    break;
            }
        }
        // Replace VariableExpression with ValueExpression
        return node.accept(this, candidates);
    }

    @Override
    public int optimizationsCount() {
        return propagatedVariables.size();
    }

    @Override
    public String summaryInfo() {
        if (optimizationsCount() == 0) return "";
        final StringBuilder sb = new StringBuilder();
        if (propagatedVariables.size() > 0) {
            sb.append("\nConstant propagations: ").append(propagatedVariables.size());
            for (Map.Entry<String, Integer> e : propagatedVariables.entrySet()) {
                sb.append("\n  ").append(e.getKey()).append(": ").append(e.getValue());
            }
        }
        return sb.toString();
    }

    @Override
    public Node visit(VariableExpression s, Map<String, Value> t) {
        if (t.containsKey(s.name)) {
            if (!propagatedVariables.containsKey(s.name)) {
                propagatedVariables.put(s.name, 1);
            } else {
                propagatedVariables.put(s.name, 1 + propagatedVariables.get(s.name));
            }
            return new ValueExpression(t.get(s.name));
        }
        return super.visit(s, t);
    }
}
