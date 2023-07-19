package com.sh8121.javatutorial.javareactive.v3_reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class AppFluxWithTwoScheduler {

    public static void main(String[] args) throws InterruptedException {
        var scheduler1 = Schedulers.newSingle("scheduler1");
        var scheduler2 = Schedulers.newSingle("scheduler2");
        var flux = Flux.just("A", "B", "C", "D")
            .publishOn(scheduler1)
            .map(data -> {
                System.out.printf("%s flowed at %s\n", data, Thread.currentThread());
                return data;
            })
            .publishOn(scheduler2);
        flux.subscribe(new MySubscriber("MySubscriber1"));
        flux.subscribe(new MySubscriber("MySubscriber2"));

        Thread.sleep(3000L);

        scheduler1.dispose();
        scheduler2.dispose();
    }

    static class MySubscriber implements Subscriber<String> {

        private String name;
        private Subscription subscription;

        public MySubscriber(String name) {
            this.name = name;
        }

        @Override
        public void onSubscribe(Subscription s) {
            this.subscription = s;
            this.subscription.request(1);
        }

        @Override
        public void onNext(String s) {
            System.out.printf("%s received %s at %s\n", name, s, Thread.currentThread());
            this.subscription.request(1);
        }

        @Override
        public void onError(Throwable t) {
            System.out.printf("%s onError at %s\n", name, Thread.currentThread());
            t.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.printf("%s onComplete at %s\n", name, Thread.currentThread());
        }
    }
}
