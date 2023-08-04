package com.sh8121.javatutorial.javamultithreading.v2_other_mechanism;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class AppSemaphore {

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

        private final Semaphore semaphore;

        public Printer() {
            semaphore = new Semaphore(3);
        }

        public void print(String text) {
            try {
                semaphore.acquire();
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
                System.out.println(text + " Printed");
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
