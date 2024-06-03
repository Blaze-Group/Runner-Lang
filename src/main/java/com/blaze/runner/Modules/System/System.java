package com.blaze.runner.Modules.System;

import com.blaze.runner.Libary.Arguments;
import com.blaze.runner.Libary.Function;
import com.blaze.runner.Programs.Runcode;
import com.blaze.runner.Shell;
import com.blaze.runner.Modules.Module;
import com.blaze.runner.Parser.Parser.Console;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Runtime.Variables;
import kotlin.UninitializedPropertyAccessException;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class System implements Module {
    @Override
    public void init() {
        MapValue SystemMap = new MapValue(5);
        MapValue Application = new MapValue(1);

        Application.set("Exit", new Function() {
            @Override
            public Value execute(Value... args) {
                Arguments.check(1, args.length);
                java.lang.System.exit(args[0].asInt());
                return NumberValue.ZERO;
            }
        });

        SystemMap.set("get", new getProperty());
        SystemMap.set("getAddress", new Function() {
            @Override
            public Value execute(Value... args) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    return new StringValue(inetAddress.getHostAddress());
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        SystemMap.set("ping", new Function() {
            @Override
            public Value execute(Value... args) {
                String URL = args[0].asString();
                Process process = null;
                try {
                    process = Runtime.getRuntime().exec("ping " + URL);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
                String line;
                while (true) {
                    try {
                        if (!((line = reader.readLine()) != null)) break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    java.lang.System.out.println(line);
                }

                int exitCode = 0;
                try {
                    exitCode = process.waitFor();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                java.lang.System.out.println("Код завершения: " + exitCode);
                return null;
            }
        });
        SystemMap.set("start", new Function() {
            @Override
            public Value execute(Value... args) {
                String path = args[0].asString();
                try {
                    Process process = Runtime.getRuntime().exec("start " + path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return NumberValue.ZERO;
            }
        });
        SystemMap.set("getScreenGeometry", new Function() {
            @Override
            public Value execute(Value... args) {
                return new ScreenValue();
            }
        });
        SystemMap.set("exec", new Function() {
            @Override
            public Value execute(Value... args) {
                final Runcode.RunOptions options;
                try {
                    options = new Runcode.RunOptions();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Runcode.RunProgram(args[0].asString(), options, "", true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return NumberValue.ZERO;
            }
        });
        SystemMap.set("execute", new Function() {
            @Override
            public Value execute(Value... args) {
                String command = args[0].asString();
                try {
                    Process process = Runtime.getRuntime().exec(command);
                } catch (IOException e){
                    throw new RuntimeException(e);
                }
                return NumberValue.ZERO;
            }
        });

        SystemMap.set("kill", new Function() {
            @Override
            public Value execute(Value... args) {
                String name = args[0].asString();
                try {
                    String full_cmd = String.format("taskkill /im %s /f", name);
                    Process process = Runtime.getRuntime().exec(full_cmd);
                } catch (IOException e){
                    throw new RuntimeException(e);
                }
                return NumberValue.ZERO;
            }
        });

        Variables.define("System", SystemMap);
        Variables.define("Application", Application);
    }

    private static class getProperty implements Function {

        @Override
        public Value execute(Value... args) {
            if(Objects.equals(args[0].asString(), "runner.version")){
                return new StringValue(Shell.getVer());
            }
            return new StringValue(java.lang.System.getProperty(args[0].asString()));
        }
    }

    private static class ScreenValue extends MapValue {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();

        public ScreenValue() {
            super(2);
            init();
        }

        private void init() {
            set("width", new NumberValue(dim.width));
            set("height", new NumberValue(dim.height));
        }
    }
}
