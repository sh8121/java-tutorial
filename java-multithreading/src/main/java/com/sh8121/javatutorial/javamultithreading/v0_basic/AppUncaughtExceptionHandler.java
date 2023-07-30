package com.sh8121.javatutorial.javamultithreading.v0_basic;

import java.lang.Thread.UncaughtExceptionHandler;

public class AppUncaughtExceptionHandler {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Integer.parseInt("123"));
            System.out.println(Integer.parseInt("234"));
            System.out.println(Integer.parseInt("345"));
            System.out.println(Integer.parseInt("XYZ"));
            System.out.println(Integer.parseInt("456"));
        };

        var thread = new Thread(runnable);
//        thread.setUncaughtExceptionHandler(new CustomExceptionHandler());
        thread.start();
    }

    static class CustomExceptionHandler implements UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("Exception occurred at " + t.getName() + " message: " + e.getMessage());
        }
    }
}
