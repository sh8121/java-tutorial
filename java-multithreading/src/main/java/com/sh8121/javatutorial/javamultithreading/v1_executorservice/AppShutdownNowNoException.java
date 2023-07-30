package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.util.concurrent.Executors;

public class AppShutdownNowNoException {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            long count = 0;
            for (long i = 0; i < 10000000000L; i++) {
                count++;
            }
            System.out.println("Task is done at " + Thread.currentThread().getName());
        };

        var executorService = Executors.newSingleThreadExecutor();

        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);

        var runnables = executorService.shutdownNow();
        System.out.println("size of waiting tasks = " + runnables.size());
    }
}
