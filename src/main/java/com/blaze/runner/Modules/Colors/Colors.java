package com.blaze.runner.Modules.Colors;

import com.blaze.runner.Libary.Function;
import com.blaze.runner.Modules.Module;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Runtime.Variables;

import java.awt.*;

public class Colors implements Module {
    @Override
    public void init() {
        MapValue ColorsMap = new MapValue(9);
        ColorsMap.set("RESET", new StringValue("\u001b[10m"));
        ColorsMap.set("RED", new StringValue("\u001b[31m"));
        ColorsMap.set("GREEN", new StringValue("\u001b[32m"));
        ColorsMap.set("BLUE", new StringValue("\u001b[34m"));
        ColorsMap.set("WHITE", new StringValue("\u001b[37m"));
        ColorsMap.set("BLACK", new StringValue("\u001b[40m"));
        ColorsMap.set("PURPLE", new StringValue("\u001b[35m"));
        ColorsMap.set("YELLOW", new StringValue("\u001b[33m"));
        ColorsMap.set("CYAN", new StringValue("\u001b[36m"));
        Variables.define("Colors", ColorsMap);
    }
}
