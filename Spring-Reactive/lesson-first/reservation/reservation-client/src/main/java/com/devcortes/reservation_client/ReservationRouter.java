package com.devcortes.reservation_client;

import com.devcortes.reservation_client.rsocket.GreetingRequest;
import com.devcortes.reservation_client.rsocket.GreetingResponse;
import com.devcortes.reservation_client.rsocket.GreetingsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class ReservationRouter {

    @Bean
    RouterFunction<ServerResponse> serverResponseRouterFunction(ReservationClient reservationClient, GreetingsClient greetingsClient) {
        return route()
                .GET("sse/greetings/{name}", new HandlerFunction<ServerResponse>() {
                    @Override
                    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
                        String name = serverRequest.pathVariable("name");
                        Flux<GreetingResponse> greet = greetingsClient.greet(new GreetingRequest(name));
                        return ServerResponse.ok()
                                .contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(greet, GreetingResponse.class);
                    }
                })
                .GET("/reservations/names", serverRequest -> {
                    Flux<String> map = reservationClient.getReservations()
                            .map(Reservation::getReservationName)
                            .onErrorResume(throwable -> Flux.just("EEEEEEK!!!!!!!!!!"))
                            .retryBackoff(5, Duration.ofSeconds(2));
                    return ServerResponse.ok().body(map, String.class);
                })
                .build();
    }
}
