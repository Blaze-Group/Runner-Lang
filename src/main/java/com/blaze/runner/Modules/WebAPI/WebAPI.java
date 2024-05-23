package com.blaze.runner.Modules.WebAPI;

import com.blaze.runner.Modules.Module;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Variables;


public final class WebAPI implements Module {

    public static void initConstants() {
    }

    @Override
    public void init() {
        MapValue HTTPMap = new MapValue(5);
        initConstants();
        HTTPMap.set("URLEncode", new URLEncode());
        HTTPMap.set("HTTP", new http_http());

        Variables.define("WebAPI", HTTPMap);
    }
}
