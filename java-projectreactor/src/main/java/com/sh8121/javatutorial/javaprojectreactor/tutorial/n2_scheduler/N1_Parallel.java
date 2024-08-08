package com.sh8121.javatutorial.javaprojectreactor.tutorial.n2_scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class N1_Parallel {

    public static void main(String[] args) throws InterruptedException {
        Logger logger = LoggerFactory.getLogger(N1_Parallel.class);

        var fluxPublisher1 = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        var fluxParallelPublisher1 = fluxPublisher1.parallel();
        fluxParallelPublisher1.subscribe(num -> logger.info("subscribed : {}", num));

        System.out.println();

        var fluxPublisher2 = Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        var fluxParallelPublisher2 = fluxPublisher2.parallel().runOn(Schedulers.parallel());
        fluxParallelPublisher2.subscribe(num -> logger.info("subscribed : {}", num));

        Thread.sleep(1000L);

        System.out.println();

        var fluxPublisher3 = Flux.fromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        var fluxParallelPublisher3 = fluxPublisher3.parallel(4).runOn(Schedulers.parallel());
        fluxParallelPublisher3.subscribe(num -> logger.info("subscribed : {}", num));

        Thread.sleep(1000L);
    }
}
