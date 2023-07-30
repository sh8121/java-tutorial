package com.sh8121.javatutorial.javamultithreading.v0_basic;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class AppCompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        var runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable Impl1");
            }
        };

        var supplier1 = new Supplier<String>() {
            @Override
            public String get() {
                return "Supplier1 Result";
            }
        };

        var completableFuture1 = CompletableFuture.runAsync(runnable1);
        var completableFuture2 = CompletableFuture.supplyAsync(supplier1);

        System.out.println(completableFuture1.get());
        System.out.println(completableFuture2.get());
    }
}
