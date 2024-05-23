package com.blaze.runner.Modules.GUI;

import com.blaze.runner.Runtime.Values.MapValue;

import java.awt.LayoutManager;

public class LayoutManagerValue extends MapValue {

    final LayoutManager layout;

    public LayoutManagerValue(LayoutManager layout) {
        super(0);
        this.layout = layout;
    }
}