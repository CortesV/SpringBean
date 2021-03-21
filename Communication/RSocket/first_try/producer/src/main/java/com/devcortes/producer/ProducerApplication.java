package com.devcortes.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;
import java.util.stream.Stream;

@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class GreetingsRequest {
    private String name;
}

@Data
class GreetingsResponse {

    private String greeting;

    public GreetingsResponse() {

    }

    GreetingsResponse(String name) {
        this.withGreeting("Hello " + name + " @ " + Instant.now());
    }

    public GreetingsResponse withGreeting(String message) {
        this.greeting = message;
        return this;
    }
}

@Controller
class ProducerController {

    @MessageExceptionHandler
    private Flux<GreetingsResponse> errorHandler(IllegalArgumentException e) {
        return Flux.just(new GreetingsResponse().withGreeting("OH NO!!!!!"));
    }

    @MessageMapping("error")
    public Flux<GreetingsResponse> error() {
        return Flux.error(new IllegalArgumentException());
    }

    @MessageMapping("greeting")
    public GreetingsResponse greeting(GreetingsRequest greetingsRequest) {
        return new GreetingsResponse(greetingsRequest.getName());
    }

    @MessageMapping("greeting-stream")
    public Flux<GreetingsResponse> greetingStream(GreetingsRequest greetingsRequest) {
        return Flux.fromStream(Stream.generate(() -> new GreetingsResponse(greetingsRequest.getName())))
                .delayElements(Duration.ofSeconds(1));
    }
}
