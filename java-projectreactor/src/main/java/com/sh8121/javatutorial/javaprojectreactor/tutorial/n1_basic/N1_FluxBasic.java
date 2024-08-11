package com.sh8121.javatutorial.javaprojectreactor.tutorial.n1_basic;

import com.sh8121.javatutorial.javaprojectreactor.tutorial.etc_util.LogUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class N1_FluxBasic {

    public static void main(String[] args) {
        var fluxPublisher1 = Flux.just(1, 2, 3, 4, 5);
        var fluxMap1 = fluxPublisher1.map(num -> num * 2);
        fluxMap1.subscribe(num -> LogUtil.info("subscribed {}", num));

        System.out.println();

        var fluxPublisher2 = Flux.fromArray(new Integer[]{1, 2, 3, 4, 5});
        var fluxFilter2 = fluxPublisher2.filter(num -> num > 2);
        var fluxMap2 = fluxFilter2.map(num -> num * 3);
        fluxMap2.subscribe(num -> LogUtil.info("subscribed {}", num));

        System.out.println();

        var monoPublisher1 = Mono.<String>justOrEmpty(null);
        var monoPublisher2 = Mono.justOrEmpty("Hello");
        var fluxPublisher3 = monoPublisher1.concatWith(monoPublisher2);
        fluxPublisher3.subscribe(str -> LogUtil.info("subscribed {}", str));

        System.out.println();

        var monoPublisher3 = Mono.just("Hello");
        var monoPublisher4 = Mono.just("World");
        var fluxPublisher4 = monoPublisher3.concatWith(monoPublisher4);
        fluxPublisher4.subscribe(
                str -> LogUtil.info("subscribed {}", str),
                err -> LogUtil.error(err.getMessage()),
                () -> LogUtil.info("onComplete")
        );

        System.out.println();

        var fluxPublisher5 = Flux.concat(Flux.just(1, 2, 3, 4, 5), Flux.fromArray(new Integer[]{6, 7, 8, 9, 10}));
        var monoPublisher5 = fluxPublisher5.collectList();
        monoPublisher5.subscribe(list -> LogUtil.info("subscribed {}", list));
    }
}
