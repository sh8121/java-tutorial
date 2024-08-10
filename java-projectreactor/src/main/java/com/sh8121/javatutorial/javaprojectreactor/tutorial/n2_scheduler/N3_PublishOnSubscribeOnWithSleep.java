package com.sh8121.javatutorial.javaprojectreactor.tutorial.n2_scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class N3_PublishOnSubscribeOnWithSleep {

    public static void main(String[] args) throws InterruptedException {

        Logger logger = LoggerFactory.getLogger(N3_PublishOnSubscribeOnWithSleep.class);

        var fluxPublisher1 = Flux.range(1, 10)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(num -> {
                    logger.info("range onNext {}", num);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .publishOn(Schedulers.boundedElastic())
                .filter(num -> num % 2 == 0)
                .doOnNext(num -> {
                    logger.info("filter onNext {}", num);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .publishOn(Schedulers.boundedElastic())
                .map(num -> num * 2)
                .doOnNext(num -> {
                    logger.info("map onNext {}", num);
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        fluxPublisher1.subscribe(num -> logger.info("subscribed1 {}", num));
//        fluxPublisher1.subscribe(num -> logger.info("subscribed2 {}", num));

        Thread.sleep(13000L);
    }
}
