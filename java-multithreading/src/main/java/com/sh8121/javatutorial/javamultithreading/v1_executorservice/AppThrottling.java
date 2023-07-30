package com.sh8121.javatutorial.javamultithreading.v1_executorservice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AppThrottling {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task done");
        };
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(10);
        var executor = new ThrottlingThreadPoolExecutor(1, 1, 5, TimeUnit.SECONDS, blockingQueue);

//        executor.prestartAllCoreThreads();

        for (int i = 0; i < 50; i++) {
//            blockingQueue.add(runnable);
            executor.execute(runnable);
            System.out.println("Task Added");
        }

        executor.shutdown();
    }

    static class ThrottlingThreadPoolExecutor extends ThreadPoolExecutor {

        private final Semaphore semaphore;

        public ThrottlingThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
            semaphore = new Semaphore(corePoolSize);
        }

        @Override
        public void execute(Runnable command) {
            boolean acquired = false;

            while (!acquired) {
                try {
                    semaphore.acquire();
                    acquired = true;
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }

            super.execute(command);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            semaphore.release();
        }
    }
}
