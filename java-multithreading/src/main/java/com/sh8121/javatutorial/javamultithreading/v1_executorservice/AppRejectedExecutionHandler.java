package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class AppRejectedExecutionHandler {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("Done");
        };

        var executorService = Executors.newFixedThreadPool(3);
        ((ThreadPoolExecutor) executorService).setRejectedExecutionHandler(new CustomRejectedExecutionHandler());

        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.submit(runnable);

        executorService.shutdown();

        try {
            executorService.submit(runnable);
        } catch (RuntimeException e) {
            System.out.println("Exception occurred, message: " + e.getMessage());
        }
    }

    static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            throw new RuntimeException("Rejected");
        }
    }
}
