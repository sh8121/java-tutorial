package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppRunnable {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(4L);
                System.out.println("Done at " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(runnable);

        var future = executorService.submit(runnable, "DONE");
        while (!future.isDone()) {
            System.out.println("Task is not done yet");
            Thread.sleep(1000L);
        }

        System.out.println("Task is done, the result is " + future.get());

        Thread.sleep(1000L);

        executorService.shutdown();
    }
}
