package com.blaze.runner;

import com.blaze.runner.Core.Information;
import com.blaze.runner.Parser.Parser.*;
import com.blaze.runner.Programs.Log;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

import static com.blaze.runner.Programs.Runcode.Run;
import static com.blaze.runner.Programs.Runcode.Debug;


public class Shell {

    public static int work = 1;

    public static String getVer(){
        return "v2.1.5 (Stable)";
    }

    public static String getRAPI() {
        return "v1.4 (RAPI Developer)";
    }

    public static String text = """
                ____                                 _____ __         ____
               / __ \\__  ______  ____  ___  _____   / ___// /_  ___  / / /
              / /_/ / / / / __ \\/ __ \\/ _ \\/ ___/   \\__ \\/ __ \\/ _ \\/ / /\s
             / _, _/ /_/ / / / / / / /  __/ /      ___/ / / / /  __/ / / \s
            /_/ |_|\\__,_/_/ /_/_/ /_/\\___/_/      /____/_/ /_/\\___/_/_/  \s
                                                                         \s
                """;

    public static int check = 0;


    public static void main(String[] args) throws Exception {
        if(args.length == 1) {
            Run(args[0]);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press enter to exit...");
            scanner.next();
            System.exit(1);
        }
        else if (args.length == 2) {
            Debug(args[0]);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press enter to exit...");
            scanner.next();
            System.exit(1);
        }

        String data = String.format("""
                %s
                Runner Version: %s
                RAPI: %s
                Author(-s): Blaze
                 
                """, text, Information.getVersion(), Information.getRAPI());

        System.out.println(data);

        while (work == 1) {
            Shell();
        }
    }

    public static void Help(){
        String HelpData = """
                
                "--exit" - Exit with Runner Shell.
                "--run [Path]" - Run file.
                "--help" - Commands.
                "--clear" - Clean Screen.
                "--log out" - Output Log.
                "--log clear" - Clear Log.
                "--get [PARAMETR]" - Get parametr.
                "--info" - Get Information.
                "--rpm" - Runner Package Manager.
                "--rpm install [Name]" - Install library
                "--rpm uninstall [Name]" - Uninstall library
                "--rpm install examples [Name_Module] [Name_file].rsc" - Install Example.
                
                """;
        System.out.println(HelpData);
    }

    public static void Shell() throws Exception {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("$> ");
        String[] cmd = sc.readLine().split(" ");
        switch (cmd[0]) {
            case "--exit", "-exit" -> work = 0;
            case "--help", "-help" -> Help();
            case "--run", "-r" -> Run(cmd[1]);
            case "--log", "-log" -> {
                switch (cmd[1]) {
                    case "clear" -> Log.Clear();
                    case "out" -> System.out.println(Log.Output());
                    default -> System.out.println("Unknown log command: " + "\"" + cmd[1] + "\". Type \"--help\".");
                }
            }
            case "--info", "-info" -> {
                System.out.println(String.format("""
                %s
                Runner Version: %s
                RAPI: %s
                Author(-s): Blaze
                
                """, text, Information.getVersion(), Information.getRAPI()));
            }
            case "--get", "-get" -> {
                switch (cmd[1]) {
                    case "rv" -> System.out.println("v2.1.5 (Stable)");
                    case "apiv" -> System.out.println("v1.4 (Stable)");
                    case "jdk" -> System.out.println("17");
                    case "author" -> System.out.println("Blaze");
                    case "lib" -> System.out.println("Lib Count: 17");
                    case "github" -> System.out.println("https://github.com/Blaze-Group");
                    case "youtube" -> System.out.println("https://youtube.com");
                    case "telegram" -> System.out.println("https://t.me/runner_lang");
                    case "UUID" -> System.out.println("192168165DESKTOP-3FVVIF7ghtwenqQDS93170");
                    case "artify" -> System.out.println("Runner");
                    case "group" -> System.out.println("com.blaze.runner");
                    case "pom" -> System.out.println("%LOCAL-PATH%: pom.xml");
                    case "libs" -> {
                        System.out.println("""
                        Array
                        Colors
                        DateTime
                        FileSystem
                        GUI
                        Graphics (In Development)
                        Interface
                        JSON
                        Loader
                        Math
                        Random
                        RSoup
                        STD
                        System
                        TFont
                        UUID
                        WebAPI
                        YAML
                        """);
                    }
                    default -> System.out.println("Unknown: " + cmd[1]);
                }
            }
            case "--rpm", "-rpm" -> {
                switch (cmd[1]) {
                    case "install" -> {
                        String name_module = cmd[2];
                        int progress = 0;

                        while (progress <= 100) {
                            System.out.print("\rInstall " + name_module + ": " + progress + "% [" + getProgressBar(progress) + "]");

                            try {
                                Thread.sleep(250);
                            } catch (InterruptedException e) {
                                System.err.println("Error: " + e.getMessage());
                            }

                            progress++;
                        }
                        if (cmd[2].equals("examples")) {
                            String pod_module = cmd[3];
                            String name_file = cmd[4];
                            try {
                                String website_url = SourceLoader.readSource("Configs\\URL.rcf");
                                URL website = new URL(website_url + name_module + "/" + pod_module + "/" + name_file);

                                Thread.sleep(100);
                                System.out.println("\nConnect to " + website_url + "...");
                                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                                if (!Files.exists(Paths.get("Examples"))) {
                                    Files.createDirectory(Paths.get("Examples"));
                                }
                                if (!Files.exists(Paths.get("Examples\\" + pod_module))) {
                                    Files.createDirectory(Paths.get("Examples\\" + pod_module));
                                }
                                FileOutputStream fos = new FileOutputStream("Examples\\" + pod_module + "\\" + name_file);
                                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                                System.out.println("\nDownload " + name_module + " complete!");
                                return;
                            } catch (Exception e) {
                                System.out.println("\nError: " + e.toString());
                                System.out.println("\nDownload " + name_module + " failed!");
                                return;
                            }
                        }
                        else {
                           try {
                                String website_url = SourceLoader.readSource("Configs\\URL.rcf");
                                URL website = new URL(website_url + name_module + ".rsc");
                                Thread.sleep(100);
                                System.out.println("\nConnect to " + website_url + "...");
                                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                                FileOutputStream fos = new FileOutputStream("Lib\\" + name_module + ".rsc");
                                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                                System.out.println("\nDownload " + name_module + " complete!");
                                return;
                            } catch (Exception e) {
                                System.out.println("\nError: " + e.toString());
                                System.out.println("\nDownload " + name_module + " failed!");
                                return;
                            }
                        }
                    }
                    case "uninstall" -> {
                        String name_module = cmd[2];
                        int progress = 0;

                        while (progress <= 100) {
                            System.out.print("\rUninstall " + name_module + ": " + progress + "% [" + getProgressBar(progress) + "]");

                            try {
                                Thread.sleep(250);
                            } catch (InterruptedException e) {
                                System.err.println("Error: " + e.getMessage());
                            }

                            progress++;
                        }
                        String path = "Lib\\" + name_module + ".rsc";
                        if (!Files.exists(Path.of(path))) {
                            System.out.println("\nUnknown library: " + name_module);
                        } else {
                            Files.delete(Path.of(path));
                            System.out.println("\nUninstall " + name_module + " complete!");
                        }
                    }
                    default -> System.out.println("Unknown command in RPM: " + "\"" + cmd[1] + "\".");
                }
            }
            case "--clear", "-clear", "-cls" -> {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
            default -> System.out.println("Unknown command: " + "\"" + cmd[0] + "\". Type \"--help\".");
        }

    }

    private static String getProgressBar(int progress) {
        StringBuilder bar = new StringBuilder();
        int filled = (int) Math.round((double) progress / 100 * 60); // 60 символов для bar
        for (int i = 0; i < filled; i++) {
            bar.append("*");
        }
        for (int i = filled; i < 20; i++) {
            bar.append(" ");
        }
        return bar.toString();
    }
}
