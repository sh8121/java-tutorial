package com.sh8121.javatutorial.javareactive.v3_reactor;

import reactor.core.publisher.Flux;

public class AppSyncFlux {

    public static void main(String[] args) {
        var flux = Flux.just("A", "B", "C", "D").log();

        flux.subscribe(data -> {
            System.out.printf("%s retrieved At %s\n", data, Thread.currentThread());
        });

        System.out.println("종료");
    }
}
