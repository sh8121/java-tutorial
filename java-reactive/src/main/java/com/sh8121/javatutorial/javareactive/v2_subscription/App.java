package com.sh8121.javatutorial.javareactive.v2_subscription;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    public static void main(String[] args) throws InterruptedException {
        var publisher = new SubmissionPublisher<String>();
        var subscriber1 = new EndSubscriber("subscriber1", 3);
        var subscriber2 = new EndSubscriber("subscriber2", 4);

        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        for (int i = 0; i < 5; i++) {
            publisher.submit("Item" + i);
        }

        Thread.sleep(3000);

        publisher.close();
    }

    static class EndSubscriber implements Subscriber<String> {

        private String name;
        private AtomicInteger number;
        private Subscription subscription;

        public EndSubscriber(String name, int number) {
            this.name = name;
            this.number = new AtomicInteger(number);
        }

        @Override
        public void onSubscribe(Subscription subscription) {
            System.out.printf("%s Subscribe At %s\n", name, Thread.currentThread());
            this.subscription = subscription;
            if (this.number.get() > 0) {
                this.subscription.request(1);
                this.number.getAndDecrement();
            }
        }

        @Override
        public void onNext(String item) {
            System.out.printf("%s Received: %s At %s\n", name, item, Thread.currentThread());
            if (this.number.get() > 0) {
                this.subscription.request(1);
                this.number.decrementAndGet();
            }
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.printf("%s Complete At %s\n", name, Thread.currentThread());
        }
    }
}
