package com.devcortes.springreactive;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ReservationRestController {

	private final ReservationRepository reservationRepository;

	@GetMapping("/reservations_old")
	public Flux<Reservation> reservationFlux() {
		return this.reservationRepository.findAll();
	}
}
