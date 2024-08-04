package com.sh8121.javatutorial.javareactive.archive.v3_reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class AppFluxMono {

    public static void main(String[] args) throws InterruptedException {
        var scheduler = Schedulers.newSingle("reactor single thread scheduler");
        var flux = Flux.just("A", "B", "C", "D").map(Mono::just).subscribeOn(scheduler);

        flux.subscribe(mono -> {
            System.out.printf("Consumer1 Received Mono At %s\n", Thread.currentThread());
            mono.subscribe(data -> {
                System.out.printf("Consumer1 Received %s At %s\n", data, Thread.currentThread());
            });
        });

        flux.subscribe(mono -> {
            System.out.printf("Consumer2 Received Mono At %s\n", Thread.currentThread());
            mono.subscribe(data -> {
                System.out.printf("Consumer2 Received %s At %s\n", data, Thread.currentThread());
            });
        });

        System.out.println("종료");

        Thread.sleep(3000);

        scheduler.dispose();
    }
}
