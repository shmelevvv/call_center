package org.example;

import java.util.Queue;
import java.util.concurrent.*;

public class Main {
    public static final int OPERATORS_COUNT = 5;

    public static void main(String[] args) throws InterruptedException {
        Queue<String> queue = new ConcurrentLinkedQueue<>();
        System.out.println(Runtime.getRuntime().availableProcessors());
        final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CallGenerator generator = new CallGenerator(queue);
        executor.execute(generator);

        for (int i = 0; i < OPERATORS_COUNT; i++) {
            Operator operator = new Operator("Thread " + i, queue);
            executor.execute(operator);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Все потоки завершены");
    }
}
