package com.devcortes.reservation_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@RepositoryRestResource
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	@RestResource (path = "by-name ")
	Collection<Reservation> findByReservationName(@Param("name") String reservationName);
}
