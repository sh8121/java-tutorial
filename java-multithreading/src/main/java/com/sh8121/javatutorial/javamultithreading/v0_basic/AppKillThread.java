package com.sh8121.javatutorial.javamultithreading.v0_basic;

public class AppKillThread {

    public static void main(String[] args) throws InterruptedException {
        var customTaskA1 = new CustomTaskA();
        var customTaskA2 = new CustomTaskA();

        customTaskA1.start();
        customTaskA2.start();

        Thread.sleep(1000);

        customTaskA1.stop();
        customTaskA2.stop();

        var customTaskB1 = new CustomTaskB();
        var customTaskB2 = new CustomTaskB();

        customTaskB1.start();
        customTaskB2.start();

        Thread.sleep(1100);

        customTaskB1.stop();
        customTaskB2.stop();
    }

    static class CustomTaskA implements Runnable {

        private volatile boolean flag = false;
        private Thread worker;

        public CustomTaskA() {
            worker = new Thread(this);
        }

        public void start() {
            worker.start();
        }

        public void stop() {
            flag = true;
        }

        @Override
        public void run() {
            while (!flag) {
                try {
                    Thread.sleep(500L);
                    System.out.println(Thread.currentThread().getName() + " Running...");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + " Interrupted " + e.getMessage());
                }
            }
            System.out.println(Thread.currentThread().getName() + " Stopped");
        }
    }

    static class CustomTaskB implements Runnable {

        private Thread worker;

        public CustomTaskB() {
            worker = new Thread(this);
        }

        public void start() {
            worker.start();
        }

        public void stop() {
            worker.interrupt();
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(500L);
                    System.out.println(Thread.currentThread().getName() + " Running...");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + " Interrupted " + e.getMessage());
                }
            }
            System.out.println(Thread.currentThread().getName() + " Stopped");
        }
    }
}
