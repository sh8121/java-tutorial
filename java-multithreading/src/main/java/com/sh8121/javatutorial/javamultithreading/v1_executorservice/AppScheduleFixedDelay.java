package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppScheduleFixedDelay {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
                System.out.println("Done At " + LocalDateTime.now());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var scheduledExecutorService = Executors.newScheduledThreadPool(5);

        var scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(runnable, 0L, 5L, TimeUnit.SECONDS);

        final int MAX_COUNT = 5;
        int waitingCount = 0;
        while (!scheduledFuture.isDone() && waitingCount < MAX_COUNT) {
            Thread.sleep(5000L);
            waitingCount++;
        }

        scheduledFuture.cancel(false);

        System.out.println("ScheduledFuture is done? " + scheduledFuture.isDone());

        scheduledExecutorService.shutdown();
    }
}
