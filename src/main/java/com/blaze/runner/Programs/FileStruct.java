package com.blaze.runner.Programs;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class FileStruct {
    public static String USER_MODULES_PATH = "Lib\\User Module";
    public static String LIB_PATH = "Lib";
    public static String CONFIGURES_PATH = "Configs";

    public static String MAIN_CONFIG;

    public static void DirectoryCreateAll() throws IOException
    {

        MAIN_CONFIG = "Configs\\URL.rcf";

        if(!Files.exists(Path.of(LIB_PATH))) {
            Files.createDirectories(Path.of(LIB_PATH));
        }

        if(!Files.exists(Path.of(USER_MODULES_PATH))) {
            Files.createDirectories(Path.of(USER_MODULES_PATH));
        }

        if(!Files.exists(Path.of(CONFIGURES_PATH))) {
            Files.createDirectories(Path.of(CONFIGURES_PATH));
        }

        CreateConfigs();
    }

    public static void CreateConfigs() throws IOException {
        if (!Files.exists(Paths.get(MAIN_CONFIG))) {
            Files.createFile(Paths.get(MAIN_CONFIG));
        }
        FileWriter writer = new FileWriter(MAIN_CONFIG);
        writer.write("https://raw.githubusercontent.com/Blaze-Group/Runner/v2.1.5/package-manager/");
        writer.flush();
    }
}
