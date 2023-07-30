package com.sh8121.javatutorial.javamultithreading.v0_basic;

public class AppDaemonThreadTermination {

    public static void main(String[] args) throws InterruptedException {
        var aThread = new AThread();
        var bThread = new BThread();

        aThread.start();

        bThread.setDaemon(true);
        bThread.start();

        Thread.sleep(2000L);
    }

    static class AThread extends Thread {

        @Override
        public void run() {
            var bThread = new BThread();
            bThread.setDaemon(true);
            bThread.start();

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    static class BThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(300L);
                    System.out.println("Execution - " + i);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
