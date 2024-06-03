package com.blaze.runner.Modules.GUI;

import com.blaze.runner.Exceptions.RNException;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.FunctionValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Values.StringValue;

import javax.swing.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;

public class JFileOpenerValue extends JComponentValue {

    final JFileChooser fileChooser;

    public JFileOpenerValue(JFileChooser fileChooser) {
        super(1, fileChooser);
        this.fileChooser = fileChooser;
        init();
    }

    private void init() {
        set("showOpenDialog", new FunctionValue(this::showOpenDialog));
        set("showSaveDialog", new FunctionValue(this::showSaveDialog));
        set("getTextFromFile", new FunctionValue(this::getTextFromFile));

    }

    private Value showOpenDialog(Value... args){
        fileChooser.showOpenDialog(null);
        return NumberValue.ZERO;
    }

    private Value showSaveDialog(Value... args){
        try {
            fileChooser.showSaveDialog(null);
        }catch(Exception ignored){}

        return NumberValue.ZERO;
    }
    private Value getTextFromFile(Value... args){
        File selectedFile = fileChooser.getSelectedFile();
        try {
            String fileContent = new String(Files.readAllBytes(selectedFile.toPath()));
            return new StringValue(fileContent);
        } catch (IOException ex) {
            throw new RNException("IOException", "");
        }
    }
}
