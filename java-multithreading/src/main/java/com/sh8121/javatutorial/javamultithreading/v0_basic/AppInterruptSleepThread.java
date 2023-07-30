package com.sh8121.javatutorial.javamultithreading.v0_basic;

public class AppInterruptSleepThread {

    public static void main(String[] args) throws InterruptedException {
        var aThread = new AThread();
        var bThread = new BThread(aThread);

        aThread.start();
        bThread.start();

        aThread.join();

        System.out.println("Main Completed");
    }

    static class AThread extends Thread {

        @Override
        public void run() {
            for (int i = 1; i <= 4; i++) {
                try {
                    Thread.sleep(500L);
                    System.out.println("AThread Execution - " + i);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    static class BThread extends Thread {

        private Thread thread;

        public BThread(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 4; i++) {
                try {
                    Thread.sleep(200L);
                    thread.interrupt();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
