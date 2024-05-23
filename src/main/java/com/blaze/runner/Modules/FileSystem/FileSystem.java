package com.blaze.runner.Modules.FileSystem;


import com.blaze.runner.Exceptions.AutoException;
import com.blaze.runner.Libary.*;
import com.blaze.runner.Modules.Module;
import com.blaze.runner.Runtime.*;
import com.blaze.runner.Parser.Parser.Console;
import com.blaze.runner.Runtime.Values.ArrayValue;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.NumberValue;
import com.blaze.runner.Runtime.Values.StringValue;

import java.io.*;

import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public final class FileSystem implements Module {

   private static Map<Integer, FileInfo> files;



    @Override
    public void init() {
        files = new HashMap<>();

        MapValue files = new MapValue(15);

        files.set("Create", new Function() {
            @Override
            public Value execute(Value... args) {
                String path = args[0].asString();
                File file = new File(path);
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    return new StringValue("Error: " + e.toString());
                }
                return NumberValue.MINUS_ONE;
            }
        });

        files.set("Delete", new Function() {
            @Override
            public Value execute(Value... args) {
                String path = args[0].asString();
                try {
                    Files.delete(Path.of(path));
                } catch (IOException e) {
                    return new StringValue("Error: " + e.toString());
                }
                return NumberValue.MINUS_ONE;
            }
        });

        files.set("Rename", new Function() {
            @Override
            public Value execute(Value... args) {
                String path = args[0].asString();
                String newName = args[1].asString();
                File file = new File(path);
                try {
                    file.renameTo(new File(newName));
                } catch (Exception e) {
                    return new StringValue("Error: " + e);
                }
                return NumberValue.MINUS_ONE;
            }
        });

        files.set("Exists", new Function() {
            @Override
            public Value execute(Value... args) {
                String path = args[0].asString();
                boolean isExists = false;
                if (!Files.exists(Path.of(path))) {
                    isExists = true;
                }
                else {
                    isExists = false;
                }
                return NumberValue.fromBoolean(isExists);
            }
        });

        files.set("isDir", fileToBoolean(File::isDirectory));
        files.set("isFile", fileToBoolean(File::isFile));

        files.set("getSize", new fileSize());

        files.set("ReadAllText", new Function() {
            @Override
            public Value execute(Value... args) {
                String path = args[0].asString();
                try {
                    String source = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
                    return new StringValue(source);
                } catch (Exception e ){
                    return new StringValue("Error: " + e);
                }
            }
        });

        files.set("WriteAllLine", new WLine());
        files.set("WriteAllText", new Function() {
            @Override
            public Value execute(Value... args) {
                String path = args[0].asString();
                String text = args[1].asString();
                try (FileWriter writer = new FileWriter(path)) {
                    writer.write(text);
                    writer.flush();
                } catch (Exception e) {
                    return new StringValue("Error: " + e.toString());
                }
                return NumberValue.ZERO;
            }
        });

        Variables.define("File", files);
    }


    
    private static class open implements Function {

        @Override
        public Value execute(Value... args) {
            Arguments.checkAtLeast(1, args.length);
            
            final File file = Console.fileInstance(args[0].asString());
            try {
                if (args.length > 1) {
                    return process(file, args[1].asString().toLowerCase());
                }
                return process(file, "r");
            } catch (IOException ioe) {
                return NumberValue.MINUS_ONE;
            }
        }
        
        private Value process(File file, String mode) throws IOException {
            DataInputStream dis = null;
            BufferedReader reader = null;
            if (mode.contains("rb")) {
                dis = new DataInputStream(new FileInputStream(file));
            } else if (mode.contains("r")) {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            }
            
            DataOutputStream dos = null;
            BufferedWriter writer = null;
            final boolean append = mode.contains("+");
            if (mode.contains("wb")) {
                dos = new DataOutputStream(new FileOutputStream(file, append));
            } else if (mode.contains("w")) {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), "UTF-8"));
            }
            
            final int key = files.size();
            files.put(key, new FileInfo(file, dis, dos, reader, writer));
            return NumberValue.of(key);
        }
    }
    
    private abstract static class FileFunction implements Function {
        
        @Override
        public Value execute(Value... args) {
            if (args.length < 1) throw new AutoException("File descriptor expected");
            final int key = args[0].asInt();
            try {
                return execute(files.get(key), args);
            } catch (IOException ioe) {
                return NumberValue.MINUS_ONE;
            }
        }
        
        protected abstract Value execute(FileInfo fileInfo, Value[] args) throws IOException;
    }

    private static class listFiles extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            return ArrayValue.of(fileInfo.file.list());
        }
    }

    private static class copy implements Function {

        @Override
        public Value execute(Value... args) {
            Arguments.check(2, args.length);
            try {
                final FileInputStream is = new FileInputStream(fileFrom(args[0]));
                final FileOutputStream os = new FileOutputStream(fileFrom(args[1]));
                final FileChannel ic = is.getChannel();
                ic.transferTo(0, ic.size(), os.getChannel());
                is.close();
                os.close();
                return NumberValue.ONE;
            } catch (IOException ioe) {
                return NumberValue.MINUS_ONE;
            }
        }
    }

    private static class rename implements Function {

        @Override
        public Value execute(Value... args) {
            Arguments.check(2, args.length);
            return NumberValue.fromBoolean( fileFrom(args[0]).renameTo(fileFrom(args[1])) );
        }
    }
    
    private static class fileSize extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            return NumberValue.of(fileInfo.file.length());
        }
    }

    
    private static class WBool extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            fileInfo.dos.writeBoolean(args[1].asInt() != 0);
            return NumberValue.ONE;
        }
    }
    
    private static class WByte extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            fileInfo.dos.writeByte((byte) args[1].asInt());
            return NumberValue.ONE;
        }
    }


    
    private static class WChar extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            final char ch = (args[1].type() == Types.NUMBER)
                    ? ((char) args[1].asInt())
                    : args[1].asString().charAt(0);
            fileInfo.dos.writeChar(ch);
            return NumberValue.ONE;
        }
    }
    
    private static class WShort extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            fileInfo.dos.writeShort((short) args[1].asInt());
            return NumberValue.ONE;
        }
    }
    
    private static class WInt extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            fileInfo.dos.writeInt(args[1].asInt());
            return NumberValue.ONE;
        }
    }
    
    private static class WLong extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            final long value;
            if (args[1].type() == Types.NUMBER) {
                value = ((NumberValue)args[1]).asLong();
            } else {
                value = (long) args[1].asNumber();
            }
            fileInfo.dos.writeLong(value);
            return NumberValue.ONE;
        }
    }
    
    private static class WFloat extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            final float value;
            if (args[1].type() == Types.NUMBER) {
                value = ((NumberValue)args[1]).asFloat();
            } else {
                value = (float) args[1].asNumber();
            }
            fileInfo.dos.writeFloat(value);
            return NumberValue.ONE;
        }
    }
    
    private static class WDouble extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            fileInfo.dos.writeDouble(args[1].asNumber());
            return NumberValue.ONE;
        }
    }
    
    private static class WUTF extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            fileInfo.dos.writeUTF(args[1].asString());
            return NumberValue.ONE;
        }
    }
    
    private static class WLine extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            fileInfo.writer.write(args[1].asString());
            fileInfo.writer.newLine();
            return NumberValue.ONE;
        }
    }

    private static class WText extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            fileInfo.writer.write(args[1].asString());
            return NumberValue.ONE;
        }
    }
    

    
    private static class close extends FileFunction {
        @Override
        protected Value execute(FileInfo fileInfo, Value[] args) throws IOException {
            if (fileInfo.dis != null) {
                fileInfo.dis.close();
            }
            if (fileInfo.dos != null) {
                fileInfo.dos.close();
            }
            if (fileInfo.reader != null) {
                fileInfo.reader.close();
            }
            if (fileInfo.writer != null) {
                fileInfo.writer.close();
            }
            return NumberValue.ONE;
        }
    }

    private static File fileFrom(Value value) {
        if (value.type() == Types.NUMBER) {
            return files.get(value.asInt()).file;
        }
        return Console.fileInstance(value.asString());
    }

    private interface FileToBooleanFunction {

        boolean apply(File file);
    }

    private static Function fileToBoolean(FileToBooleanFunction f) {
        return args -> {
            Arguments.check(1, args.length);
            return NumberValue.fromBoolean(f.apply(fileFrom(args[0])));
        };
    }
    
    private static class FileInfo {
        File file;
        DataInputStream dis;
        DataOutputStream dos;
        BufferedReader reader;
        BufferedWriter writer;

        public FileInfo(File file, DataInputStream dis, DataOutputStream dos, BufferedReader reader, BufferedWriter writer) {
            this.file = file;
            this.dis = dis;
            this.dos = dos;
            this.reader = reader;
            this.writer = writer;
        }
    }
}
