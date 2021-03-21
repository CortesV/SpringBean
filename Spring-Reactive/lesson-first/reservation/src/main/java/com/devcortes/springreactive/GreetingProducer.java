package com.devcortes.springreactive;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Component
public class GreetingProducer {

    Flux<GreetingResponse> greet (GreetingRequest greetingRequest) {
        return Flux
                .fromStream(Stream.generate(() -> new GreetingResponse("Hello " + greetingRequest.getName() + " @ " + Instant.now() + " !!!!!")))
                .delayElements(Duration.ofSeconds(1));
    }
}
