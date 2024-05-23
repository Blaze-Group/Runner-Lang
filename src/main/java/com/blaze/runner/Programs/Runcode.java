package com.blaze.runner.Programs;

import com.blaze.runner.Exceptions.StopException;
import com.blaze.runner.Shell;
import com.blaze.runner.Parser.AST.Statement;
import com.blaze.runner.Parser.Parser.*;
import com.blaze.runner.Runtime.TimeMeasurement;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Runcode {

    public static void Run(String input) throws Exception {
        final RunOptions options = new RunOptions();
        RunProgram(SourceLoader.readSource(input), options, input);
    }



    public static void RunProgram(String input, RunOptions options, String Path) throws Exception {
        Log.Clear();
        Log.Append("Start Compile file: " + Path);
        options.validate();
        final TimeMeasurement measurement = new TimeMeasurement();
        measurement.start("Tokenize time");
        final List<Token> tokens = Lexer.tokenize(input);
        Log.AppendMultiLine(SourceLoader.readSource(Path), "Source Code: ");
        measurement.stop("Tokenize time");
        if (options.showTokens) {
            final int tokensCount = tokens.size();
            for (int i = 0; i < tokensCount; i++) {
                System.out.println(i + " " + tokens.get(i));
            }
        }

        measurement.start("Parse time");
        final Parser parser = new Parser(tokens);
        final Statement parsedProgram = parser.parse();
        measurement.stop("Parse time");
        if (options.showAst) {
            System.out.println(parsedProgram.toString());
        }
        if (parser.getParseErrors().hasErrors()) {
            System.out.println(parser.getParseErrors());
            return;
        }
        if (options.lintMode) {
            Linter.lint(parsedProgram);
            return;

        }
        final Statement program;
        if (options.optimizationLevel > 0) {
            measurement.start("Optimization time");
            program = Optimizer.optimize(parsedProgram, options.optimizationLevel, options.showAst);
            measurement.stop("Optimization time");
            System.out.println(program.toString());
            if (options.showAst) {
                System.out.println(program.toString());
            }
        } else {
            program = parsedProgram;
        }
        program.accept(new FunctionAdder());

        try {
            measurement.start("Execution time");
            program.execute();
        } catch (StopException ex) {
            Log.AppendError(ex.toString(), "main");
            // skip
        } catch (Exception ex) {
            Console.handleException(Thread.currentThread(), ex);
        }
        finally {
            if (options.showMeasurements) {
                measurement.stop("Execution time");
                System.out.println("======================");
                System.out.println(measurement.summary(TimeUnit.MILLISECONDS, true));



            }

        }

        Log.AppendMultiLine("Done! Program end. MS: " + measurement.summary(TimeUnit.MILLISECONDS, true), "");
        System.out.println(" ");
    }

    public static class RunOptions {
        public boolean showTokens, showAst, showMeasurements;
        public boolean lintMode;
        public boolean beautifyMode;
        public int optimizationLevel;

        RunOptions() throws IOException {
            showTokens = false;
            showAst = false;
            showMeasurements = false;
            lintMode = false;
            beautifyMode = false;
            optimizationLevel = 0;
        }

        void validate() {
            if (lintMode) {
                showTokens = false;
                showAst = false;
                showMeasurements = false;
                optimizationLevel = 0;
            }
        }
    }
}
