package com.devcortes.springreactive;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
@RequiredArgsConstructor
public class RSocketGreetingsController {

    private GreetingProducer greetingProducer;

    @MessageMapping("greetings")
    Flux<GreetingResponse> greetingResponseFlux(GreetingRequest greetingRequest) {
        return this.greetingProducer.greet(greetingRequest);
    }
}
