package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppInvokeAll {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<String> callable1 = () -> {
            TimeUnit.SECONDS.sleep(2L);
            return "Callable1 Done";
        };

        Callable<String> callable2 = () -> {
            TimeUnit.SECONDS.sleep(4L);
            return "Callable2 Done";
        };

        Callable<String> callable3 = () -> {
            TimeUnit.SECONDS.sleep(6L);
            return "Callable3 Done";
        };

        var executorService = Executors.newFixedThreadPool(3);

        List<Callable<String>> callableList = new ArrayList<>();
        callableList.add(callable1);
        callableList.add(callable2);
        callableList.add(callable3);

        System.out.println("Before InvokeAll At " + LocalDateTime.now());
        var futureList = executorService.invokeAll(callableList);
        System.out.println("After InvokeAll At " + LocalDateTime.now());

        executorService.shutdown();

        System.out.println(futureList.get(0).get());
        System.out.println(futureList.get(1).get());
        System.out.println(futureList.get(2).get());
    }
}
