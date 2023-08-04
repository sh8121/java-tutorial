package com.sh8121.javatutorial.javamultithreading.v2_other_mechanism;

import java.util.concurrent.ConcurrentLinkedDeque;

public class AppConcurrentLinkedDeque {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedDeque<String> queue = new ConcurrentLinkedDeque<>();
        var threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(new AddRunnable(queue));
            threads[i].start();
        }

        for (int i = 0; i < 100; i++) {
            threads[i].join();
        }

        System.out.println("After Adding, the size of queue is " + queue.size());

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(new RemoveRunnable(queue));
            threads[i].start();
        }

        for (int i = 0; i < 100; i++) {
            threads[i].join();
        }

        System.out.println("After Removing, the size of queue is " + queue.size());
    }

    static class AddRunnable implements Runnable {

        private final ConcurrentLinkedDeque<String> queue;

        public AddRunnable(ConcurrentLinkedDeque<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                queue.add(Thread.currentThread().getName() + "-" + i);
            }
        }
    }

    static class RemoveRunnable implements Runnable {

        private final ConcurrentLinkedDeque<String> queue;

        public RemoveRunnable(ConcurrentLinkedDeque<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5000; i++) {
                queue.pollFirst();
                queue.pollLast();
            }
        }
    }
}
