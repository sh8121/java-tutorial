package com.sh8121.javatutorial.javamultithreading.v2_other_mechanism;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        var countDownLatch = new CountDownLatch(5);
        var executorService = Executors.newFixedThreadPool(5);

        executorService.execute(new CountDownLatchRunnable("Task-1", 5, countDownLatch));
        executorService.execute(new CountDownLatchRunnable("Task-2", 4, countDownLatch));
        executorService.execute(new CountDownLatchRunnable("Task-3", 3, countDownLatch));
        executorService.execute(new CountDownLatchRunnable("Task-4", 6, countDownLatch));
        executorService.execute(new CountDownLatchRunnable("Task-5", 7, countDownLatch));

        countDownLatch.await();

        System.out.println("All Tasks are done");

        executorService.shutdown();
    }

    static class CountDownLatchRunnable implements Runnable {

        private final String name;
        private final long sleepSeconds;
        private final CountDownLatch countDownLatch;

        public CountDownLatchRunnable(String name, long sleepSeconds, CountDownLatch countDownLatch) {
            this.name = name;
            this.sleepSeconds = sleepSeconds;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(sleepSeconds);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println(name + " CountDownLatchRunnable Done");
                countDownLatch.countDown();
            }
        }
    }
}
