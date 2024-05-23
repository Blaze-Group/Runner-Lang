package com.blaze.runner.Parser.Parser.Debug;

import com.blaze.runner.Programs.Log;

public final class ParseError {

    private final int line;
    private final Exception exception;

    public ParseError(int line, Exception exception) {
        this.line = line;
        this.exception = exception;
    }

    public int getLine() {
        return line;
    }

    public Exception getException() {
        return exception;
    }

    @Override
    public String toString() {
        String RED = "\033[41;30m";
        String WHITE = "\033[40;37m";
        String error_data = String.format(RED + """
                Fatal Error (line: %s):
                    %s
                
                If you don`t know . Please read documentation for use error! Or typed Author`s personal message!
                https://github.com/Blaze-Group/Runner
                """ + WHITE, line, exception.getMessage());
        try {
            Log.AppendError(error_data, "Main");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return error_data;
    }
}
