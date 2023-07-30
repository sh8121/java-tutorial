package com.sh8121.javatutorial.javamultithreading.v0_basic;

public class AppThread {

    public static void main(String[] args) {
        var thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("Thread extending Thread");
            }
        };

        var thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread implementing Runnable");
            }
        });

        var thread3 = new Thread(() -> System.out.println("Thread implementing Lambda Runnable"));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
