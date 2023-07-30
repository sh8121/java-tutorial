package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppSchedule {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<String> callable = () -> "Callable Done At " + LocalDateTime.now();

        var scheduledExecutorService = Executors.newScheduledThreadPool(5);

        var scheduledFuture = scheduledExecutorService.schedule(callable, 10L, TimeUnit.SECONDS);
        System.out.println("Call Schedule At " + LocalDateTime.now());

        while (!scheduledFuture.isDone()) {
            System.out.println("Task is not done yet, remaining delay is " + scheduledFuture.getDelay(TimeUnit.MILLISECONDS));
            TimeUnit.SECONDS.sleep(1L);
        }

        System.out.println("Task is done, result is " + scheduledFuture.get());

        scheduledExecutorService.shutdown();
    }
}
