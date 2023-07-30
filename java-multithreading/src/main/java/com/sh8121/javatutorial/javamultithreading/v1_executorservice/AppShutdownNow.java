package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.util.concurrent.Executors;

public class AppShutdownNow {

    public static void main(String[] args) {
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

        var runnables = executorService.shutdownNow();
        System.out.println("size of waiting tasks = " + runnables.size());
    }
}
