package com.sh8121.javatutorial.javamultithreading.v2_other_mechanism;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class AppForkJoinPoolLog {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var forkJoinPool = (ForkJoinPool) Executors.newWorkStealingPool();
        var calculateTask = new CalculateTask(1, 100000000000L);
        forkJoinPool.submit(calculateTask);

        while (!calculateTask.isDone()) {
            System.out.println("=============================");
            System.out.println("Parallelism: " + forkJoinPool.getParallelism());
            System.out.println("Active Thread Count: " + forkJoinPool.getActiveThreadCount());
            System.out.println("Queued Task Count: " + forkJoinPool.getQueuedTaskCount());
            System.out.println("Work Stealing Count: " + forkJoinPool.getStealCount());
            System.out.println("=============================");

            Thread.sleep(1000L);
        }

        System.out.println("Done. Result is " + calculateTask.get());
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
