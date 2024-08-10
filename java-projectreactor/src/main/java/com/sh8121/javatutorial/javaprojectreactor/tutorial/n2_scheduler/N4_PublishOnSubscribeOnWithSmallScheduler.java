package com.sh8121.javatutorial.javaprojectreactor.tutorial.n2_scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class N4_PublishOnSubscribeOnWithSmallScheduler {

    public static void main(String[] args) throws InterruptedException {

        Logger logger = LoggerFactory.getLogger(N4_PublishOnSubscribeOnWithSmallScheduler.class);

        var newBoundedElasticScheduler = Schedulers.newBoundedElastic(2, 2, "NewBoundedElasticScheduler", 1, true);

        var fluxPublisher1 = Flux.range(1, 10)
                .subscribeOn(newBoundedElasticScheduler)
                .doOnNext(num -> {
                    logger.info("range onNext {}", num);
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .publishOn(newBoundedElasticScheduler)
                .filter(num -> num % 2 == 0)
                .doOnNext(num -> {
                    logger.info("filter onNext {}", num);
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .publishOn(newBoundedElasticScheduler)
                .map(num -> num * 2)
                .doOnNext(num -> {
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    logger.info("map onNext {}", num);
                });

        fluxPublisher1.subscribe(num -> logger.info("subscribed {}", num));

        Thread.sleep(10000L);
    }
}
