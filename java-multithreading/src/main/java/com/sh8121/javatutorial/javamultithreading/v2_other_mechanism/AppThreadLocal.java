package com.sh8121.javatutorial.javamultithreading.v2_other_mechanism;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AppThreadLocal {

    public static void main(String[] args) {
        var runnable = new CustomRunnable();
        var executorService = Executors.newFixedThreadPool(10);

        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);

        executorService.shutdown();
    }

    static class CustomRunnable implements Runnable {

        private final AtomicInteger counter = new AtomicInteger(0);
        private final ThreadLocal<Integer> counterPerThread = new ThreadLocal<>();

        @Override
        public void run() {
            counterPerThread.set(counter.incrementAndGet());
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("Done. The Counter is %d, but The Counter of Thread is %d\n", counter.get(), counterPerThread.get());
        }
    }
}
