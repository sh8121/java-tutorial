package com.sh8121.javatutorial.javamultithreading.v0_basic;

import java.util.ArrayList;
import java.util.List;

public class AppWaitNotify {

    public static void main(String[] args) {
        List<Integer> taskQueue = new ArrayList<>();
        var producerThread = new Thread(new Producer(taskQueue), "Producer");
        var consumerThread = new Thread(new Consumer(taskQueue), "Consumer");

        producerThread.start();
        consumerThread.start();
    }

    static class Producer implements Runnable {

        private final List<Integer> taskQueue;
        private final int MAX_CAPACITY = 5;

        public Producer(List<Integer> taskQueue) {
            this.taskQueue = taskQueue;
        }

        @Override
        public void run() {
            int counter = 0;
            while (true) {
                try {
                    produce(counter++);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }

        private void produce(int task) throws InterruptedException {
            synchronized (taskQueue) {
                while (taskQueue.size() == MAX_CAPACITY) {
                    System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting, task size = " + taskQueue.size());
                    taskQueue.wait();
                }

                Thread.sleep(1000L);
                taskQueue.add(task);
                System.out.println("Produced: " + task);
                taskQueue.notifyAll();
            }
        }
    }

    static class Consumer implements Runnable {

        private final List<Integer> taskQueue;

        public Consumer(List<Integer> taskQueue) {
            this.taskQueue = taskQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    consume();
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }

        private void consume() throws InterruptedException {
            synchronized (taskQueue) {
                while (taskQueue.size() == 0) {
                    System.out.println("Queue is empty " + Thread.currentThread().getName() + " is waiting, task size = " + taskQueue.size());
                    taskQueue.wait();
                }

                Thread.sleep(1000L);
                int task = taskQueue.remove(0);
                System.out.println("Consumed: " + task);
                taskQueue.notifyAll();
            }
        }
    }
}
