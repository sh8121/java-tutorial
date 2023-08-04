package com.sh8121.javatutorial.javamultithreading.v2_other_mechanism;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class AppForkJoinPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var executorService = Executors.newFixedThreadPool(1);
        var calculateCallable = new CalculateCallable(1, 100000000000L);
        var future = executorService.submit(calculateCallable);

        System.out.println("CalculateCallable submitted at " + LocalDateTime.now());
        long result = future.get();
        System.out.println("CalculateCallable completed at " + LocalDateTime.now() + " result is " + result);

        var forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();
        var calculateTask = new CalculateTask(1, 100000000000L);
        forkJoinPool.submit(calculateTask);

        System.out.println("CalculateTask submitted at " + LocalDateTime.now());
        result = calculateTask.get();
        System.out.println("CalculateTask completed at " + LocalDateTime.now() + " result is " + result);

        executorService.shutdown();
    }

    static class CalculateCallable implements Callable<Long> {

        private final long startNum;
        private final long endNum;

        public CalculateCallable(long startNum, long endNum) {
            this.startNum = startNum;
            this.endNum = endNum;
        }

        @Override
        public Long call() throws Exception {
            long result = 0;

            for (long num = startNum; num <= endNum; num++) {
                result++;
            }

            return result;
        }
    }

    static class CalculateTask extends RecursiveTask<Long> {

        private final long startNum;
        private final long endNum;

        public CalculateTask(long startNum, long endNum) {
            this.startNum = startNum;
            this.endNum = endNum;
        }

        @Override
        protected Long compute() {
            long result = 0;

            if (endNum - startNum >= 1000000000) {
                long halfNum = (startNum + endNum) / 2;
                var task1 = new CalculateTask(startNum, halfNum);
                var task2 = new CalculateTask(halfNum + 1, endNum);
                task1.fork();
                task2.fork();
                result += task1.join();
                result += task2.join();
            } else {
                for (long num = startNum; num <= endNum; num++) {
                    result++;
                }
            }

            return result;
        }
    }
}
