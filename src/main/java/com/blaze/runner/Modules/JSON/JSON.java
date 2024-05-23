package com.blaze.runner.Modules.JSON;

import com.blaze.runner.Modules.Module;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Variables;


public final class JSON implements Module {

    public static void initConstants() {
    }

    @Override
    public void init() {
        MapValue JSONMap = new MapValue(10);
        JSONMap.set("Encode", new JSONEncode());
        JSONMap.set("Decode", new JSONDecode());
        initConstants();
        Variables.define("JSON", JSONMap);
    }
}
