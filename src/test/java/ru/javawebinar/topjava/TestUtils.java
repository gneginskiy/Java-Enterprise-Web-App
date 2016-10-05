package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.text.DecimalFormat;

public final class TestUtils {
    private TestUtils() {}

    public static TestRule getTestTimer() {
        return new TestWatcher() {
            long timeStart;

            protected void starting(Description description) {
                timeStart = System.currentTimeMillis();
            }

            protected void finished(Description description) {
                long timeEnd = System.currentTimeMillis();
                double seconds = (timeEnd - timeStart) / 1000.0;
                System.out.println("\n===============================================================================================================================");
                System.out.println("                                             Test completed - ran in: " + new DecimalFormat("0.000").format(seconds) + " sec");
                System.out.println("===============================================================================================================================\n");
            }
        };
    }
}