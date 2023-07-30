package com.sh8121.javatutorial.javamultithreading.v0_basic;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppScheduledExecutorService {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable Impl1");
            }
        };

        Runnable runnable2 = () -> System.out.println("Runnable Impl2");

        var scheduledExecutorService = Executors.newScheduledThreadPool(10);

        var scheduledFuture1 = scheduledExecutorService.schedule(runnable1, 3000L, TimeUnit.MILLISECONDS);
        var scheduledFuture2 = scheduledExecutorService.schedule(runnable2, 1000L, TimeUnit.MILLISECONDS);

        scheduledFuture1.get();
        System.out.printf("ScheduledFuture1 Completed At %s\n", LocalDateTime.now());
        scheduledFuture2.get();
        System.out.printf("ScheduledFuture2 Completed At %s\n", LocalDateTime.now());

        scheduledExecutorService.shutdown();
    }
}
