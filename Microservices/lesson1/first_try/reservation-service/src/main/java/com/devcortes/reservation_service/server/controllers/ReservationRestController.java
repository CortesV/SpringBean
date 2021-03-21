package com.devcortes.reservation_service.server.controllers;

import com.mybookingpal.dataaccesslayer.dao.ReservationDao;
import com.mybookingpal.dataaccesslayer.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ReservationRestController {

	@Autowired
	private ReservationDao reservationDao;

	@GetMapping("/reservations/{reservation_id}")
	public ResponseEntity<Reservation> getReservation(@PathVariable("reservation_id") Integer id) {
		return new ResponseEntity<>(reservationDao.read(), HttpStatus.OK);
	}

	@GetMapping("/reservations")
	public ResponseEntity<List<Reservation>> getReservations() {
		return new ResponseEntity<>(reservationDao.readAll(), HttpStatus.OK);
	}
}
