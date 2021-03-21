package com.devcortes.reservationclient.server.controllers;

import com.mybookingpal.dataaccesslayer.entity.Reservation;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest/reservations")
public class ReservationApiGatewayRestController {

	@Autowired
	private RestTemplate restTemplate;

	//@HystrixCommand(fallbackMethod = "fallbackSingle")
	@GetMapping("/{reservation_id}")
	public ResponseEntity<Reservation> getReservation(@PathVariable("reservation_id") Integer id) {
		ResponseEntity<Reservation> exchange = restTemplate.exchange("http://first-client-layer-service/rest/reservations/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<Reservation>() {
		});
		return exchange;
	}

	//@HystrixCommand(fallbackMethod = "fallback")
	@GetMapping("")
	public ResponseEntity<List<Reservation>> getReservationsWithZuul() {
		ResponseEntity<List<Reservation>> exchange = restTemplate.exchange("http://first-client-layer-service/rest/reservations", HttpMethod.GET, null, new ParameterizedTypeReference<List<Reservation>>() {
		});
		return exchange;
	}

	public ResponseEntity<List<Reservation>> fallback() {
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<Reservation> fallbackSingle(Integer id) {
		return new ResponseEntity<>(new Reservation(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
