package com.blaze.runner.Parser.Preprocessor;

import com.blaze.runner.Core.Information;
import com.blaze.runner.Programs.Runcode;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Preprocessor {
    public static String preprocess(String code) {
        List<String> lines = Arrays.asList(code.split("\n"));
        Map<String, String> macros = new HashMap<>();

        macros.put("Runner", "\"" + Information.getVersion() + "\"");

        for (String line : lines) {
            List<String> parts = Arrays.asList(line.split(" "));
            if (parts.isEmpty()) continue;

            String command = parts.get(0);
            switch (command) {
                case "#define":
                    String id = parts.get(1);
                    String replacement = String.join(" ", parts.subList(2, parts.size()));
                    if (id.contains("(")) {
                        code = "\n" + "func " + id + " return " + replacement + "\n" + code;
                        continue;
                    }
                    macros.put(id, replacement);
                    break;
                default:
                    break;
            }
        }

        lines = Arrays.asList(code.split("\n"));
        StringBuilder result = new StringBuilder();

        for (String line : lines) {
            String buffer = line;
            List<String> parts = Arrays.asList(line.split(" "));
            if (parts.get(0).equals("#undef")) {
                String idUndef = parts.get(1);
                macros.remove(idUndef);
                continue;
            }

            for (Map.Entry<String, String> entry : macros.entrySet()) {
                buffer = buffer.replaceAll(";", " ;");
                buffer = buffer.replaceAll(String.format("(?<!\")(%s)(?!\")", entry.getKey()), entry.getValue()) + "\n";
            }
            result.append(buffer);
        }

        result = new StringBuilder(result.toString()
                .replace("#define", "# PROCESSED DEFINE MACROS")
                .replace("#undef", "# PROCESSED UNDEF MACRO"));

        return result.toString();
    }
}
