package com.blaze.runner.Modules.RSoup;

import com.blaze.runner.Libary.Function;
import com.blaze.runner.Modules.Module;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Runtime.Variables;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RSoup implements Module {

    public static String docs = "";
    public static Document docum;
    public static Elements element;
    public static Element elem;

    @Override
    public void init() {
        MapValue RSoupMap = new MapValue(5);
        RSoupMap.set("Parse", new pars());
        RSoupMap.set("Select", new select());
        RSoupMap.set("Body", new body());

        Variables.define("RSoup", RSoupMap);
    }

    private static class body implements Function {
        @Override
        public Value execute(Value... args) {
            if (docum != null) {
                return new StringValue(docum.body().toString());
            } else {
                return new StringValue("Document not parsed yet");
            }
        }
    }

    private static class pars implements Function {
        @Override
        public Value execute(Value... args) {
            Document doc = null;
            try {
                doc = Jsoup.connect(args[0].toString()).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            docs = doc.toString();
            Variables.set("document", new StringValue((docs).getBytes(StandardCharsets.UTF_8).toString()));
            docum = doc;
            return new StringValue(docum.toString());


        }
    }


    private static class select implements Function {
        @Override
        public Value execute(Value... args) {
            Elements divs = docum.select(args[0].toString());
            Variables.set("elements", new StringValue((divs.toString()).getBytes(StandardCharsets.UTF_8).toString()));
            element = divs;
            return new StringValue(element.toString());
        }
    }
}
