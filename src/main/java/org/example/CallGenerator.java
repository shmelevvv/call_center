package org.example;

import java.util.Queue;

public class CallGenerator extends Thread {
    public static final int CALLS_FREQUENCY = 1000;
    public static final int GENERATION_DURATION = 60_000;

    private final Queue<String> queue;

    public CallGenerator(Queue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int passedTime = 0;
        int callNumber = 0;
        while (passedTime < GENERATION_DURATION) {
            ++callNumber;
            String callName = callNumber + " звонок";
            System.out.println("Поступил " + callName);
            queue.offer(callName);
            try {
                sleep(CALLS_FREQUENCY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            passedTime +=CALLS_FREQUENCY;
        }
    }
}
