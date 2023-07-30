package com.sh8121.javatutorial.javamultithreading.v0_basic;

public class AppJoinThread {

    public static void main(String[] args) throws InterruptedException {
        var aThread = new AThread();

        aThread.start();

//        aThread.join();
        aThread.join(1000L);

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
}
