package com.sh8121.javatutorial.javareactive.v0_basic;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class App {

    public static void main(String[] args) throws InterruptedException {
        var publisher = new SubmissionPublisher<String>();
        var subscriber1 = new EndSubscriber("subscriber1");
        var subscriber2 = new EndSubscriber("subscriber2");
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
        private Subscription subscription;

        public EndSubscriber(String name) {
            this.name = name;
        }

        @Override
        public void onSubscribe(Subscription subscription) {
            System.out.printf("%s Subscribe At %s\n", name, Thread.currentThread());
            this.subscription = subscription;
            this.subscription.request(1);
        }

        @Override
        public void onNext(String item) {
            System.out.printf("%s Received: %s At %s\n", name, item, Thread.currentThread());
            subscription.request(1);
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
