package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolClass {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Task(1));
        executor.submit(new Task(2));
        executor.submit(new Task(3));
        executor.submit(new Task(4));
        executor.shutdown();
    }
}
