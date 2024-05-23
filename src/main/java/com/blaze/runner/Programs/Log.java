package com.blaze.runner.Programs;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class Log {
    public static int errors = 0;

    public static String LOG_PATH = "Logs\\Log.txt";

    public static void Append(String text) throws Exception {
        LocalDateTime time = LocalDateTime.now();
        if(!Files.exists(Path.of("Logs\\Log.txt"))) {
            Files.createDirectories(Path.of("Logs"));
            File file = new File("Logs\\Log.txt");
            file.createNewFile();
        }
        FileWriter writer = new FileWriter("Logs\\Log.txt", true);
        writer.write("[" + time + "]: " + text + "\n");
        writer.flush();
    }

    public static void AppendMultiLine(String text, String name) throws Exception {
        LocalDateTime time = LocalDateTime.now();
        if(!Files.exists(Path.of("Logs\\Log.txt"))) {
            Files.createDirectories(Path.of("Logs"));
            File file = new File("Logs\\Log.txt");
            file.createNewFile();
        }
        String data = String.format("""
                [%s]: %s
                %s
                """, time, name, text + "\n");
        FileWriter writer = new FileWriter("Logs\\Log.txt", true);
        writer.write(data);
        writer.flush();
    }

    public static void Clear() throws Exception {
        if(!Files.exists(Path.of("Logs\\Log.txt"))) {
            Files.createDirectories(Path.of("Logs"));
            File file = new File("Logs\\Log.txt");
            file.createNewFile();
        }
        FileWriter writer = new FileWriter("Logs\\Log.txt", false);
        writer.write("");
        writer.flush();
    }

    public static void AppendError(String text, String className) throws Exception {
        LocalDateTime time = LocalDateTime.now();
        if(!Files.exists(Path.of("Logs\\Log.txt"))) {
            Files.createDirectories(Path.of("Logs"));
            File file = new File("Logs\\Log.txt");
            file.createNewFile();
        }
        FileWriter writer = new FileWriter("Logs\\Log.txt", true);
        errors++;
        writer.write(String.format("""
                [%s]: 
                Fatal Error:
                    %s
                    
                    Class: %s
                    If you don`t know . Please read documentation for use error! Or typed Author`s personal message!
                    https://github.com/Blaze-Group/Runner
                """, time, text + "\n", className));
        writer.flush();
    }

    public static String Output() throws IOException {
        if(!Files.exists(Path.of("Logs\\Log.txt"))) {
            Files.createDirectories(Path.of("Logs"));
            File file = new File("Logs\\Log.txt");
            file.createNewFile();
        }
        String source = new String(Files.readAllBytes(Paths.get("Logs\\Log.txt")), StandardCharsets.UTF_8);
        return source;
    }
}
