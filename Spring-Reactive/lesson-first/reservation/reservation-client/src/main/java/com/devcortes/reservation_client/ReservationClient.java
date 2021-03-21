package com.devcortes.reservation_client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class ReservationClient {

    private final WebClient webClient;

    Flux<Reservation> getReservations() {
        return this.webClient
                .get()
                .uri("localhost:8080/reservations")
                .retrieve()
                .bodyToFlux(Reservation.class);
    }
}
