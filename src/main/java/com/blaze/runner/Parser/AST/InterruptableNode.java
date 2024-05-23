package com.blaze.runner.Parser.AST;

import com.blaze.runner.Exceptions.StopException;

public abstract class InterruptableNode implements Node {

    public static final int RUNNING = 0, PAUSED = 1, STOPPED = 2;

    private static volatile int state;

    public static void run() {
        state = RUNNING;
    }

    public static void pause() {
        state = PAUSED;
    }

    public static void stop() {
        state = STOPPED;
    }

    protected void interruptionCheck() {
        if (state == RUNNING) return;
        if (state == STOPPED) {
            throw new StopException();
        }
        try {
            while (state == PAUSED) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException ioe) {
            Thread.currentThread().interrupt();
        }
    }
}
