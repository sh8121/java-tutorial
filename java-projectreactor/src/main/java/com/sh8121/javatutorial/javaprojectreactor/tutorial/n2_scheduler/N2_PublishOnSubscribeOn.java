package com.sh8121.javatutorial.javaprojectreactor.tutorial.n2_scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class N2_PublishOnSubscribeOn {

    public static void main(String[] args) throws InterruptedException {
        Logger logger = LoggerFactory.getLogger(N2_PublishOnSubscribeOn.class);

        var fluxPublisher1 = Flux.range(1, 100)
                .doOnNext(num -> logger.info("range onNext {}", num))
                .publishOn(Schedulers.parallel())
                .filter(num -> num > 50)
                .doOnNext(num -> logger.info("filter onNext {}", num))
                .publishOn(Schedulers.parallel())
                .map(num -> num * 10)
                .doOnNext(num -> logger.info("map onNext {}", num));

        fluxPublisher1.subscribe(num -> logger.info("subscribed {}", num));

        Thread.sleep(1000L);

        System.out.println();
        System.out.println();

        fluxPublisher1.subscribeOn(Schedulers.parallel())
                .subscribe(num -> logger.info("subscribed {}", num));

        Thread.sleep(1000L);
    }
}
