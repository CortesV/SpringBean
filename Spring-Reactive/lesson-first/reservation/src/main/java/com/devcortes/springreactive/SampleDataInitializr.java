package com.devcortes.springreactive;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Component
@Log4j2
@RequiredArgsConstructor
public class SampleDataInitializr {

	private final ReservationRepository reservationRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void go() {

		Flux<Reservation> saved = Flux
				.just("Oleh", "Josh", "Marcin", "Jakub", "Olga", "Violetta", "Cornelia")
				.map(name -> new Reservation(null, name))
				.flatMap(this.reservationRepository::save);

		this.reservationRepository
				.deleteAll()
				.thenMany(saved)
				.thenMany(this.reservationRepository.findAll())
				.subscribe(log::info);

	}
}
