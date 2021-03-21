package com.devcortes.reservationclient.server.controllers;

import com.devcortes.reservation_service.grpc.GrpcReservationServiceGrpc;
import com.devcortes.reservation_service.grpc.Reservation;
import com.devcortes.reservation_service.grpc.ReservationList;
import com.devcortes.reservation_service.grpc.ReservationRequest;
import com.devcortes.reservationclient.server.utils.GrpcUtils;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/grpc/reservations")
public class ReservationApiGatewayGrpcController {

	@Autowired
	private ManagedChannel reservationServiceChannel;

	@Autowired
	private GrpcUtils grpcUtils;

	@GetMapping("/{reservation_id}")
	public ResponseEntity<com.mybookingpal.dataaccesslayer.entity.Reservation> getReservation(@PathVariable("reservation_id") Integer id) {
		GrpcReservationServiceGrpc.GrpcReservationServiceBlockingStub reservationServiceBlockingStub = GrpcReservationServiceGrpc.newBlockingStub(reservationServiceChannel);
		ReservationRequest request = ReservationRequest.newBuilder().setReservationId(id).build();
		Reservation reservation = reservationServiceBlockingStub.getById(request);
		return new ResponseEntity<>(convertGrpcReservationToDBReservation(reservation), HttpStatus.OK);
	}

	private com.mybookingpal.dataaccesslayer.entity.Reservation convertGrpcReservationToDBReservation(Reservation reservation) {
		com.mybookingpal.dataaccesslayer.entity.Reservation response = new com.mybookingpal.dataaccesslayer.entity.Reservation();
		response.setId(reservation.getId());
		response.setParentId(reservation.getParentId());
		response.setOrganizationId(reservation.getOrganizationId());
		response.setAgentId(reservation.getAgentId());
		response.setCustomerId(reservation.getCustomerId());
		response.setServiceId(reservation.getServiceId());
		response.setActorId(reservation.getActorId());
		response.setProductId(reservation.getProductId());
		response.setAltPartyId(reservation.getAltPartyId());
		response.setAltId(reservation.getAltId());
		response.setFinanceId(reservation.getFinanceId());
		response.setName(reservation.getName());
		response.setState(reservation.getState() );
		response.setFlat(reservation.getFlat());
		response.setMarket(reservation.getMarket());
		response.setOutcome(reservation.getOutcome());
		response.setUnit(reservation.getUnit());
		response.setArrivalTime(reservation.getArrivalTime());
		response.setDepartureTime(reservation.getDepartureTime());
		response.setServiceFrom(reservation.getServiceFrom());
		response.setServiceTo(reservation.getServiceTo());
		response.setDeposit(reservation.getDeposit());
		response.setAdult(reservation.getAdult());
		response.setChild(reservation.getChild());
		response.setInfant(reservation.getInfant());
		response.setQuantity(reservation.getQuantity());
		response.setPrice(reservation.getPrice());
		response.setQuote(reservation.getQuote());
		response.setCost(reservation.getCost());
		response.setExtra(reservation.getExtra());
		response.setCurrency(reservation.getCurrency());
		response.setCardholder(reservation.getCardholder());
		response.setCardNumber(reservation.getCardNumber());
		response.setCardMonth(reservation.getCardMonth() != null ? reservation.getCardMonth() : "");
		response.setCardYear(reservation.getCardYear() != null ? reservation.getCardYear() : "");
		response.setCardCode(reservation.getCardCode() != null ? reservation.getCardCode() : "");
		response.setServicePayer(reservation.getServicePayer() != null ? reservation.getServicePayer() : "");
		response.setTermsAccepted(reservation.getTermsAccepted());
		response.setNotes(reservation.getNotes());
		response.setGroupId(reservation.getGroupId());
		response.setDate(grpcUtils.toLocalDateTime(reservation.getDate()));
		response.setFromDate(grpcUtils.toLocalDate(reservation.getFromDate()));
		response.setToDate(grpcUtils.toLocalDate(reservation.getToDate()));
		response.setDueDate(grpcUtils.toLocalDate(reservation.getDueDate()));
		response.setDoneDate(grpcUtils.toLocalDate(reservation.getDoneDate()));
		response.setVersion(grpcUtils.toLocalDateTime(reservation.getVersion()));
		return response;
	}

	@GetMapping("")
	public ResponseEntity<List<com.mybookingpal.dataaccesslayer.entity.Reservation>> getReservationsWithZuul() {
		GrpcReservationServiceGrpc.GrpcReservationServiceBlockingStub reservationServiceBlockingStub = GrpcReservationServiceGrpc.newBlockingStub(reservationServiceChannel);
		ReservationList reservation = reservationServiceBlockingStub.getAll(Empty.newBuilder().build());
		List<com.mybookingpal.dataaccesslayer.entity.Reservation> reservations = reservation.getReservationsList().stream().map(this::convertGrpcReservationToDBReservation).collect(Collectors.toList());
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}
}
