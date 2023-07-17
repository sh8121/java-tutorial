package com.sh8121.javatutorial.javareactive.v3_reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class AppAsyncFlux {

    public static void main(String[] args) throws InterruptedException {
//        var scheduler = Schedulers.newSingle("reactor single thread scheduler");
        var scheduler = Schedulers.newParallel("reactor parallel thread scheduler", 2);
//        var scheduler = ForkJoinPoolScheduler.create("reactor forkjoinpool scheduler", 2);

        var flux = Flux.just("A", "B", "C", "D").log().map(data -> data + data).subscribeOn(scheduler);

        flux.subscribe(data -> {
            System.out.printf("Consumer1 %s retrieved At %s\n", data, Thread.currentThread());
        });

        flux.subscribe(data -> {
            System.out.printf("Consumer2 %s retrieved At %s\n", data, Thread.currentThread());
        });

        flux.subscribe(data -> {
            System.out.printf("Consumer3 %s retrieved At %s\n", data, Thread.currentThread());
        });

        System.out.println("종료");

        Thread.sleep(2000);

        scheduler.dispose();
    }
}
