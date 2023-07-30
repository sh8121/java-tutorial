package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.util.concurrent.Executors;

public class AppShutdown {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000L);
                System.out.println("Task is done at " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var executorService = Executors.newSingleThreadExecutor();

        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);

        executorService.shutdown();

        System.out.println("isShutdown? " + executorService.isShutdown());
        System.out.println("isTerminated? " + executorService.isTerminated());

        Thread.sleep(3000L);

        System.out.println("isShutdown? " + executorService.isShutdown());
        System.out.println("isTerminated? " + executorService.isTerminated());

        Thread.sleep(3000L);

        System.out.println("isShutdown? " + executorService.isShutdown());
        System.out.println("isTerminated? " + executorService.isTerminated());
    }
}
