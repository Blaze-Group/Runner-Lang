package com.blaze.runner.Parser.Parser;

import com.blaze.runner.Libary.CallStack;
import com.blaze.runner.Programs.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Console {

    private Console() { }
    
    private static OutputSettings outputSettings = new OutSettings();

    public static void useSettings(OutputSettings outputSettings) {
        Console.outputSettings = outputSettings;
    }

    public static OutputSettings getSettings() {
        return outputSettings;
    }

    public static String newline() {
        return outputSettings.newline();
    }

    public static void print(String value) {
        outputSettings.print(value);
    }

    public static void print(Object value) {
        outputSettings.print(value);
    }

    public static void println() {
        outputSettings.println();
    }

    public static void println(String value) {
        outputSettings.println(value);
    }

    public static void println(Object value) {
        outputSettings.println(value);
    }

    public static String text() {
        return outputSettings.getText();
    }

    public static void error(Throwable throwable) {
        outputSettings.error(throwable);
    }

    public static void error(CharSequence value) {
        outputSettings.error(value);
    }

    public static void handleException(Thread thread, Throwable throwable) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try(final PrintStream ps = new PrintStream(baos)) {
            String RED = "\033[41;30m";
            String WHITE = "\033[40;37m";
            ps.printf(RED + """
                    Fatal Error:
                        %s
                        
                    Class: %s
                    If you don`t know . Please read documentation for use error! Or typed Author`s personal message!
                    https://github.com/Blaze-Group/Runner
                    """ + WHITE, throwable.getMessage(), thread.getName());
            for (CallStack.CallInfo call : CallStack.getCalls()) {
                ps.printf("\tat %s%n", call);
            }
            ps.println();
            //throwable.printStackTrace(ps);
            ps.flush();
        }
        try {
            error(baos.toString("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            error(baos.toString());
        }
    }

    public static File fileInstance(String path) {
        return outputSettings.fileInstance(path);
    }
}
