package com.devcortes.reservation_service.grpc;

import com.devcortes.reservation_service.grpc.utils.GrpcUtils;
import com.google.protobuf.Empty;
import com.google.protobuf.util.Timestamps;
import com.mybookingpal.dataaccesslayer.dao.ReservationDao;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@GRpcService
public class GrpcReservationService extends GrpcReservationServiceGrpc.GrpcReservationServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrpcReservationService.class);

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private GrpcUtils grpcUtils;

    @Override
    public void getById(ReservationRequest request, StreamObserver<Reservation> responseObserver) {
        LOGGER.info("----------------------- GET GRPC REQUEST FOR RETRIEVING RESERVATION BY ID-----------------------");
        com.mybookingpal.dataaccesslayer.entity.Reservation reservation = reservationDao.read();
        Reservation response = convertDBReservationToGrpcReservation(reservation);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private Reservation convertDBReservationToGrpcReservation(com.mybookingpal.dataaccesslayer.entity.Reservation reservation) {
        Reservation response = Reservation.newBuilder()
                .setId(reservation.getId() != null ? reservation.getId().intValue() : 0)
                .setParentId(reservation.getParentId() != null ? reservation.getParentId().intValue() : 0)
                .setOrganizationId(reservation.getOrganizationId() != null ? reservation.getOrganizationId().intValue() : 0)
                .setAgentId(reservation.getAgentId() != null ? reservation.getAgentId().intValue() : 0)
                .setCustomerId(reservation.getCustomerId() != null ? reservation.getCustomerId().intValue() : 0)
                .setServiceId(reservation.getServiceId() != null ? reservation.getServiceId().intValue() : 0)
                .setActorId(reservation.getActorId() != null ? reservation.getActorId().intValue() : 0)
                .setProductId(reservation.getProductId() != null ? reservation.getProductId().intValue() : 0)
                .setAltPartyId(reservation.getAltPartyId() != null ? reservation.getAltPartyId() : "")
                .setAltId(reservation.getAltId() != null ? reservation.getAltId() : "")
                .setFinanceId(reservation.getFinanceId() != null ? reservation.getFinanceId().intValue() : 0)
                .setName(reservation.getName() != null ? reservation.getName() : "")
                .setState(reservation.getState() != null ? reservation.getState() : "")
                .setFlat(reservation.getFlat() != null ? reservation.getFlat() : "")
                .setMarket(reservation.getMarket() != null ? reservation.getMarket() : "")
                .setOutcome(reservation.getOutcome() != null ? reservation.getOutcome() : "")
                .setUnit(reservation.getUnit() != null ? reservation.getUnit() : "")
                .setArrivalTime(reservation.getArrivalTime() != null ? reservation.getArrivalTime() : "")
                .setDepartureTime(reservation.getDepartureTime() != null ? reservation.getDepartureTime() : "")
                .setServiceFrom(reservation.getServiceFrom() != null ? reservation.getServiceFrom() : "")
                .setServiceTo(reservation.getServiceTo() != null ? reservation.getServiceTo() : "")
                .setDeposit(reservation.getDeposit() != null ? reservation.getDeposit().doubleValue() : 0d)
                .setAdult(reservation.getAdult() != null ? reservation.getAdult().intValue() : 0)
                .setChild(reservation.getChild() != null ? reservation.getChild().intValue() : 0)
                .setInfant(reservation.getInfant() != null ? reservation.getInfant().intValue() : 0)
                .setQuantity(reservation.getQuantity() != null ? reservation.getQuantity().intValue() : 0)
                .setPrice(reservation.getPrice() != null ? reservation.getPrice().doubleValue() : 0d)
                .setQuote(reservation.getQuote() != null ? reservation.getQuote().doubleValue() : 0d)
                .setCost(reservation.getCost() != null ? reservation.getCost().doubleValue() : 0d)
                .setExtra(reservation.getExtra() != null ? reservation.getExtra().doubleValue() : 0d)
                .setCurrency(reservation.getCurrency() != null ? reservation.getCurrency() : "")
                .setCardholder(reservation.getCardholder() != null ? reservation.getCardholder() : "")
                .setCardNumber(reservation.getCardNumber() != null ? reservation.getCardNumber() : "")
                .setCardMonth(reservation.getCardMonth() != null ? reservation.getCardMonth() : "")
                .setCardYear(reservation.getCardYear() != null ? reservation.getCardYear() : "")
                .setCardCode(reservation.getCardCode() != null ? reservation.getCardCode() : "")
                .setServicePayer(reservation.getServicePayer() != null ? reservation.getServicePayer() : "")
                .setTermsAccepted(reservation.getTermsAccepted() != null ? reservation.getTermsAccepted().booleanValue() : false)
                .setNotes(reservation.getNotes() != null ? reservation.getNotes() : "")
                .setGroupId(reservation.getGroupId() != null ? reservation.getGroupId().longValue() : 0L)
                .setDate(grpcUtils.getTimestamp(reservation.getDate()))
                .setFromDate(grpcUtils.getTimestamp(reservation.getFromDate()))
                .setToDate(grpcUtils.getTimestamp(reservation.getToDate()))
                .setDueDate(grpcUtils.getTimestamp(reservation.getDueDate()))
                .setDoneDate(grpcUtils.getTimestamp(reservation.getDoneDate()))
                .setVersion(grpcUtils.getTimestamp(reservation.getVersion()))
                .build();
        return response;
    }

    @Override
    public void getAll(Empty request, StreamObserver<ReservationList> responseObserver) {
        List<com.mybookingpal.dataaccesslayer.entity.Reservation> reservations = reservationDao.readAll();
        List<Reservation> reservationList = reservations.stream().map(this::convertDBReservationToGrpcReservation).collect(Collectors.toList());
        ReservationList response = ReservationList.newBuilder().addAllReservations(reservationList).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
