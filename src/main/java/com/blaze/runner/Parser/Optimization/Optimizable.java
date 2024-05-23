package com.blaze.runner.Parser.Optimization;

import com.blaze.runner.Parser.AST.Node;

public interface Optimizable {

    Node optimize(Node node);

    int optimizationsCount();

    String summaryInfo();
}
