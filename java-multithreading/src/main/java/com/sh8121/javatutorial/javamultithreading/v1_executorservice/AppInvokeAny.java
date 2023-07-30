package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppInvokeAny {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable1 = () -> {
            TimeUnit.SECONDS.sleep(5L);
            return "Callable1 Done";
        };

        Callable<String> callable2 = () -> {
            try {
                TimeUnit.SECONDS.sleep(10L);
                return "Callable2 Done";
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                throw e;
            }
        };

        Callable<String> callable3 = () -> {
            try {
                TimeUnit.SECONDS.sleep(15L);
                return "Callable3 Done";
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                throw e;
            }
        };

        var executorService = Executors.newFixedThreadPool(3);

        List<Callable<String>> callableList = new ArrayList<>();
        callableList.add(callable1);
        callableList.add(callable2);
        callableList.add(callable3);

        System.out.println("Before InvokeAny At " + LocalDateTime.now());
        var result = executorService.invokeAny(callableList);
        System.out.println("After InvokeAny At " + LocalDateTime.now() + " result is " + result);

        executorService.shutdown();
    }
}
