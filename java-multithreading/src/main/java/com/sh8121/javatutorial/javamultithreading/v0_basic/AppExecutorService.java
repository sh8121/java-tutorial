package com.sh8121.javatutorial.javamultithreading.v0_basic;

import java.util.concurrent.Executors;

public class AppExecutorService {

    public static void main(String[] args) {
        var runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable Impl1");
            }
        };

        Runnable runnable2 = () -> System.out.println("Runnable Impl2");

        var executorService = Executors.newFixedThreadPool(10);

        executorService.execute(runnable1);
        executorService.execute(runnable2);

        executorService.shutdown();
    }
}
