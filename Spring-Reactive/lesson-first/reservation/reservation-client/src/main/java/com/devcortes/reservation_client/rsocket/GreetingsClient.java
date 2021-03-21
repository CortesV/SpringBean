package com.devcortes.reservation_client.rsocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class GreetingsClient {
    private final RSocketRequester rSocketRequester;

    public Flux<GreetingResponse> greet(GreetingRequest greetingRequest) {
        return this.rSocketRequester.route("greetings")
                .data(greetingRequest)
                .retrieveFlux(GreetingResponse.class);
    }
}
