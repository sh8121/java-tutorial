package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppScheduleFixedRate {

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

        var scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(runnable, 0L, 5L, TimeUnit.SECONDS);

        Thread.sleep(21000L);
        scheduledExecutorService.shutdown();
    }
}
