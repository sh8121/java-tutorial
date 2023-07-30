package com.sh8121.javatutorial.javamultithreading.v0_basic;

public class AppYieldThread {

    public static void main(String[] args) {
        //-XX:ActiveProcessorCount=1
        var aThread1 = new AThread("AThread1");
        var aThread2 = new AThread("AThread2");

        aThread1.setPriority(Thread.MAX_PRIORITY);
        aThread2.setPriority(Thread.MIN_PRIORITY);

        aThread1.start();
        aThread2.start();
    }

    static class AThread extends Thread {

        private final String name;

        public AThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(name + "Run Called");
//                Thread.yield();
                //With/Without yield
            }
        }
    }
}
