package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AppCallable {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<String> callable = () -> {
            TimeUnit.MILLISECONDS.sleep(5000L);
            return "Done at " + Thread.currentThread().getName();
        };

        var executorService = Executors.newFixedThreadPool(5);

        var future = executorService.submit(callable);

        try {
            future.get(1L, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("Task is not done, TimeoutException occurred");
        }

        while (!future.isDone()) {
            System.out.println("Task is not done yet");
            Thread.sleep(1000L);
        }

        System.out.println(future.get());

        executorService.shutdown();
    }
}
