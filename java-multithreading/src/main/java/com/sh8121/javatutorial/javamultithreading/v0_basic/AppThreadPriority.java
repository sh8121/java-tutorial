package com.sh8121.javatutorial.javamultithreading.v0_basic;

public class AppThreadPriority {

    public static void main(String[] args) {
        System.out.println("Main Thread Priority is " + Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(4);
        System.out.println("Main Thread Priority is " + Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        System.out.println("Main Thread Priority is " + Thread.currentThread().getPriority());
        try {
            Thread.currentThread().setPriority(100);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        System.out.println("Main Thread Priority is " + Thread.currentThread().getPriority());

        var thread1 = new Thread();
        System.out.println("thread1 priority is " + thread1.getPriority());

        Thread.currentThread().setPriority(4);

        var thread2 = new Thread();
        System.out.println("thread2 priority is " + thread2.getPriority());
    }
}
