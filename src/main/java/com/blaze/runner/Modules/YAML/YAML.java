package com.blaze.runner.Modules.YAML;

import com.blaze.runner.Libary.Function;
import com.blaze.runner.Modules.Module;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Runtime.Variables;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class YAML implements Module {
    @Override
    public void init() {
        MapValue YAMLMap = new MapValue(5);
        YAMLMap.set("getString", new Function() {
            @Override
            public Value execute(Value... args) {
                try {
                    InputStream inputStream = new FileInputStream(args[0].asString());
                    Yaml yaml = new Yaml();
                    Map<String, Object> config = yaml.load(inputStream);
                    String par_name = args[1].asString();
                    String result = (String) config.get(par_name);
                    return new StringValue(result);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        YAMLMap.set("getInt", new Function() {
            @Override
            public Value execute(Value... args) {
                try {
                    InputStream inputStream = new FileInputStream(args[0].asString());
                    Yaml yaml = new Yaml();
                    Map<String, Object> config = yaml.load(inputStream);
                    String par_name = args[1].asString();
                    int result = (Integer) config.get(par_name);
                    return new NumberValue(result);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        YAMLMap.set("getDouble", new Function() {
            @Override
            public Value execute(Value... args) {
                try {
                    InputStream inputStream = new FileInputStream(args[0].asString());
                    Yaml yaml = new Yaml();
                    Map<String, Object> config = yaml.load(inputStream);
                    String par_name = args[1].asString();
                    double result = (Double) config.get(par_name);
                    return new NumberValue(result);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Variables.define("YAML", YAMLMap);
    }
}
