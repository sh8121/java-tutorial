package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppShutdownNowAndAwait {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println("Task is done at " + LocalDateTime.now());
        };

        var scheduledExecutorService = Executors.newScheduledThreadPool(3);

        var future1 = scheduledExecutorService.schedule(runnable, 5L, TimeUnit.SECONDS);
        var future2 = scheduledExecutorService.schedule(runnable, 10L, TimeUnit.SECONDS);
        var future3 = scheduledExecutorService.schedule(runnable, 15L, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(7L);

        System.out.println("Future1 is done? " + future1.isDone());
        System.out.println("Future2 is done? " + future2.isDone());
        System.out.println("Future3 is done? " + future3.isDone());

        scheduledExecutorService.shutdownNow();
        System.out.println("Call ShutdownNow");

        var awaitResult = scheduledExecutorService.awaitTermination(10L, TimeUnit.SECONDS);
        System.out.println("Result of Wait : " + awaitResult);

        System.out.println("Future1 is done? " + future1.isDone());
        System.out.println("Future2 is done? " + future2.isDone());
        System.out.println("Future3 is done? " + future3.isDone());
    }
}
