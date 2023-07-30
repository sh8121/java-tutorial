package com.sh8121.javatutorial.javamultithreading.v0_basic;

import java.util.concurrent.Executors;

public class AppThreadName {

    public static void main(String[] args) {
        var threadNameRunnable = new ThreadNameRunnable();

        var thread1 = new Thread(threadNameRunnable);
        var thread2 = new Thread(threadNameRunnable);
        thread2.setName("CustomThread-1");
        var thread3 = new Thread(threadNameRunnable, "CustomThread-2");
        thread1.start();
        thread2.start();
        thread3.start();

        var executorService1 = Executors.newFixedThreadPool(3);
        var executorService2 = Executors.newFixedThreadPool(3);

        executorService1.submit(threadNameRunnable);
        executorService1.submit(threadNameRunnable);
        executorService1.submit(threadNameRunnable);
        executorService2.submit(threadNameRunnable);
        executorService2.submit(threadNameRunnable);
        executorService2.submit(threadNameRunnable);

        executorService1.shutdown();
        executorService2.shutdown();
    }

    static class ThreadNameRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
