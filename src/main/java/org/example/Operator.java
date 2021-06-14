package org.example;

import java.util.Queue;

public class Operator extends Thread {
    public static final int WORK_IN_SECONDS = 5;
    public static final int WAITING_FOR_CALL = CallGenerator.CALLS_FREQUENCY * 3;
    private final Queue<String> queue;

    public Operator(String threadName, Queue<String> queue) {
        super(threadName);
        this.queue = queue;
    }

    @Override
    public void run() {
        String tmp;
        int waitingTime = 0;
        while ((tmp = queue.poll()) != null || waitingTime <= WAITING_FOR_CALL) {
            if (tmp != null) {
                waitingTime = 0;
                System.out.println(getName() + " -> " + tmp);
                try {
                    sleep(WORK_IN_SECONDS * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                waitingTime += CallGenerator.CALLS_FREQUENCY;
                try {
                    sleep(CallGenerator.CALLS_FREQUENCY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
