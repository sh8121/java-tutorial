package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AppCustomThreadPoolExecutor {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task done");
        };

        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(); //ArrayBlockingQueue, SynchronousQueue...
        var executor = new CustomThreadPoolExecutor(
            10,
            20,
            5,
            TimeUnit.SECONDS,
            blockingQueue,
            new ThreadPoolExecutor.AbortPolicy()); //Different Policies...

        executor.prestartAllCoreThreads();

        System.out.println("Pool Size: " + executor.getPoolSize());
        System.out.println("Active Count: " + executor.getActiveCount());
        System.out.println("Queue Size: " + blockingQueue.size());

        System.out.println("add 10 tasks...");
        for (int i = 0; i < 10; i++) {
            blockingQueue.add(runnable);
        }

        System.out.println("Pool Size: " + executor.getPoolSize());
        System.out.println("Active Count: " + executor.getActiveCount());
        System.out.println("Queue Size: " + blockingQueue.size());

        System.out.println("add 10 more tasks...");
        for (int i = 0; i < 10; i++) {
            blockingQueue.add(runnable);
        }

        System.out.println("Pool Size: " + executor.getPoolSize());
        System.out.println("Active Count: " + executor.getActiveCount());
        System.out.println("Queue Size: " + blockingQueue.size());

        System.out.println("add 20 more tasks...");
        for (int i = 0; i < 20; i++) {
            blockingQueue.add(runnable);
        }

        System.out.println("Pool Size: " + executor.getPoolSize());
        System.out.println("Active Count: " + executor.getActiveCount());
        System.out.println("Queue Size: " + blockingQueue.size());

        executor.shutdown();
    }

    static class CustomThreadPoolExecutor extends ThreadPoolExecutor {

        public CustomThreadPoolExecutor(
            int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            System.out.println("Before Execute At " + Thread.currentThread().getName());
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            if (t != null) {
                System.out.println("Exception At " + Thread.currentThread().getName());
            }
            System.out.println("After Execute At " + Thread.currentThread().getName());
        }
    }
}
