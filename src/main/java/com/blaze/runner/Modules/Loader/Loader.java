package com.blaze.runner.Modules.Loader;

import com.blaze.runner.Libary.Arguments;
import com.blaze.runner.Libary.Function;
import com.blaze.runner.Libary.Types;
import com.blaze.runner.Modules.GUI.WindowValue;
import com.blaze.runner.Modules.Module;
import com.blaze.runner.Parser.Parser.Console;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.FunctionValue;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Variables;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Loader implements Module {
    @Override
    public void init() {
        MapValue LoaderMap = new MapValue(5);
        LoaderMap.set("load", new Download());

        Variables.define("Loader", LoaderMap);
    }

    private static class Download implements Function {

        @Override
        public Value execute(Value... args) {
            try {
                URL website = new URL(args[0].asString());
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream(args[1].asString());
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return NumberValue.ZERO;
        }
    }
}
