package com.sh8121.javatutorial.javamultithreading.v2_other_mechanism;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AppLock {

    public static void main(String[] args) {
        var executorService = Executors.newFixedThreadPool(10);
        var printer = new Printer();

        executorService.execute(new PrintingRunnable("Hello", printer));
        executorService.execute(new PrintingRunnable("World", printer));
        executorService.execute(new PrintingRunnable("God", printer));
        executorService.execute(new PrintingRunnable("Halo", printer));
        executorService.execute(new PrintingRunnable("No", printer));
        executorService.execute(new PrintingRunnable("Good", printer));
        executorService.execute(new PrintingRunnable("Trust", printer));
        executorService.execute(new PrintingRunnable("Shot", printer));
        executorService.execute(new PrintingRunnable("Give", printer));
        executorService.execute(new PrintingRunnable("Gimme", printer));

        executorService.shutdown();
    }

    static class PrintingRunnable implements Runnable {

        private final String text;
        private final Printer printer;

        public PrintingRunnable(String text, Printer printer) {
            this.text = text;
            this.printer = printer;
        }

        @Override
        public void run() {
            printer.print(text);
        }
    }

    static class Printer {

        private final Lock lock = new ReentrantLock();

        public void print(String text) {
            lock.lock();
            try {
                Thread.sleep(1000L);
                System.out.println(text + " Printed At " + LocalDateTime.now());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}
