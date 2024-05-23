package com.blaze.runner.Libary;

import com.blaze.runner.Exceptions.AutoException;

public final class Arguments {

    private Arguments() { }

    public static void check(int expected, int got) {
        if (got != expected) throw new AutoException(String.format(
                "%d %s expected, got %d", expected, pluralize(expected), got));
    }

    public static void checkAtLeast(int expected, int got) {
        if (got < expected) throw new AutoException(String.format(
                "At least %d %s expected, got %d", expected, pluralize(expected), got));
    }

    public static void checkOrOr(int expectedOne, int expectedTwo, int got) {
        if (expectedOne != got && expectedTwo != got)
            throw new AutoException(String.format(
                    "%d or %d arguments expected, got %d", expectedOne, expectedTwo, got));
    }

    public static void checkRange(int from, int to, int got) {
        if (from > got || got > to)
            throw new AutoException(String.format(
                    "From %d to %d arguments expected, got %d", from, to, got));
    }

    private static String pluralize(int count) {
        return (count == 1) ? "argument" : "arguments";
    }
}
