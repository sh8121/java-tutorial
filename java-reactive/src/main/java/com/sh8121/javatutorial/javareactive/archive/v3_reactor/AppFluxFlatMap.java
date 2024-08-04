package com.sh8121.javatutorial.javareactive.archive.v3_reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class AppFluxFlatMap {

    public static void main(String[] args) throws InterruptedException {
        var flux = Flux.just("A", "B", "C", "D");
        var flattenedFlux = flux.flatMap(data -> {
            System.out.printf("%s flattened at %s\n", data, Thread.currentThread());
            return Mono.just(data)
                .subscribeOn(Schedulers.parallel())
                .doOnNext(d -> {
                    System.out.printf("Mono doOnNext of %s at %s\n", d, Thread.currentThread());
                });
        });
        flattenedFlux.subscribe(data -> {
            System.out.printf("%s subscribed at %s\n", data, Thread.currentThread());
        });

        Thread.sleep(1000);
    }
}
