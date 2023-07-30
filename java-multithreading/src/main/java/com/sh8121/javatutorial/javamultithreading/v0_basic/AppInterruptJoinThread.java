package com.sh8121.javatutorial.javamultithreading.v0_basic;

public class AppInterruptJoinThread {

    public static void main(String[] args) {
        var aThread = new AThread();
        var bThread = new BThread(Thread.currentThread());

        aThread.start();
        bThread.start();

        try {
            aThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main Interrupted");
        }

        for (int i = 1; i <= 4; i++) {
            System.out.println("Main Execution - " + i);
        }
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
            try {
                Thread.sleep(1100L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            thread.interrupt();
        }
    }
}
