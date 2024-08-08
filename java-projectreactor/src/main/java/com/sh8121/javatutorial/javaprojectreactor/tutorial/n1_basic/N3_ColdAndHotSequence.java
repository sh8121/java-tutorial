package com.sh8121.javatutorial.javaprojectreactor.tutorial.n1_basic;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class N3_ColdAndHotSequence {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(N3_ColdAndHotSequence.class);

        var fluxColdPublisher = Flux.fromIterable(Arrays.asList(1, 2, 3, 4, 5));
        var fluxColdMap = fluxColdPublisher.map(num -> num * 3);
        fluxColdMap.subscribe(num -> logger.info("subscribed first {}", num));
        fluxColdMap.subscribe(num -> logger.info("subscribed second {}", num));

        System.out.println();

        var fluxHotPublisher = Flux.fromStream(Stream.of(1, 2, 3, 4, 5))
                .delayElements(Duration.ofSeconds(1)).share();
        fluxHotPublisher.subscribe(num -> logger.info("subscribed first {}", num));

        try {
            Thread.sleep(2500L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        fluxHotPublisher.subscribe(num -> logger.info("subscribed second {}", num));

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
