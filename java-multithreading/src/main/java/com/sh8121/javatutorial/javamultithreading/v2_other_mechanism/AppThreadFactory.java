package com.sh8121.javatutorial.javamultithreading.v2_other_mechanism;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class AppThreadFactory {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2000L);
                System.out.println("Done At " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var executorService = Executors.newFixedThreadPool(5);
        ((ThreadPoolExecutor) executorService).setThreadFactory(new CustomThreadFactory("Custom-"));

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
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);

        executorService.shutdown();
    }

    static class CustomThreadFactory implements ThreadFactory {

        private final AtomicInteger counter;
        private final String prefix;

        public CustomThreadFactory(String prefix) {
            this.counter = new AtomicInteger(0);
            this.prefix = prefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, prefix + counter.incrementAndGet());
        }
    }
}
