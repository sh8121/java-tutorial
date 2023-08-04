package com.sh8121.javatutorial.javamultithreading.v2_other_mechanism;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class AppPipeStream {

    public static void main(String[] args) throws IOException, InterruptedException {
        PipedReader pipedReader = new PipedReader();
        PipedWriter pipedWriter = new PipedWriter();

        pipedWriter.connect(pipedReader);

        var readerThread = new Thread(new PipeReaderRunnable(pipedReader));
        var writerThread = new Thread(new PipeWriterRunnable(pipedWriter));

        readerThread.start();
        writerThread.start();
    }

    static class PipeReaderRunnable implements Runnable {

        private final PipedReader pipedReader;

        public PipeReaderRunnable(PipedReader pipedReader) {
            this.pipedReader = pipedReader;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int val = pipedReader.read();
                    if (val == -1) {
                        System.out.println("Stream is empty, waiting...");
                        break;
                    } else {
                        System.out.print((char) val);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class PipeWriterRunnable implements Runnable {

        private final PipedWriter pipedWriter;

        public PipeWriterRunnable(PipedWriter pipedWriter) {
            this.pipedWriter = pipedWriter;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) {
                    pipedWriter.write("Test Data...\n");
                    pipedWriter.flush();
                    Thread.sleep(2000L);
                }
                pipedWriter.close();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
