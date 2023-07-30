package com.sh8121.javatutorial.javamultithreading.v0_basic;

public class AppDaemonThread {

    public static void main(String[] args) {
        var aThread1 = new AThread("aThread1");
        var aThread2 = new AThread("aThread2");

        aThread1.setDaemon(true);

        aThread1.start();
        aThread2.start();

        try {
            aThread2.setDaemon(true);
        } catch (IllegalThreadStateException e) {
            System.out.println(e);
        }

        try {
            Thread.currentThread().setDaemon(true);
        } catch (IllegalThreadStateException e) {
            System.out.println(e);
        }
    }

    static class AThread extends Thread {

        private String name;

        public AThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            if (isDaemon()) {
                System.out.println(name + " is a Daemon Thread");
                var aThread = new AThread("AThread");
                aThread.start();
            } else {
                System.out.println(name + " is a User Thread");
            }
        }
    }
}
