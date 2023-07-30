package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppShutdownBestPractice {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(5000L);
                System.out.println("Task is done at " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var executorService = Executors.newSingleThreadExecutor();

        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);

        terminate(executorService);
    }

    private static void terminate(ExecutorService executorService) {
        System.out.println("Shutdown and Wait...");
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(8L, TimeUnit.SECONDS)) {
                System.out.println("Orderly Shutdown Failed, Call ShutdownNow...");
                executorService.shutdownNow();
                if (!executorService.awaitTermination(8L, TimeUnit.SECONDS)) {
                    System.out.println("Termination Failed");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
