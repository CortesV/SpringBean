package com.devcortes.second_client_layer.grpc;

import com.devcortes.reservation_service.grpc.GrpcReservationServiceGrpc;
import com.devcortes.reservation_service.grpc.Reservation;
import com.devcortes.reservation_service.grpc.ReservationList;
import com.devcortes.reservation_service.grpc.ReservationRequest;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class SecondLayerGrpcReservationService extends GrpcReservationServiceGrpc.GrpcReservationServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecondLayerGrpcReservationService.class);

    @Autowired
    private ManagedChannel reservationServiceChannel;

    @Override
    public void getById(ReservationRequest request, StreamObserver<Reservation> responseObserver) {
        GrpcReservationServiceGrpc.GrpcReservationServiceBlockingStub reservationServiceBlockingStub = GrpcReservationServiceGrpc.newBlockingStub(reservationServiceChannel);
        Reservation reservation = reservationServiceBlockingStub.getById(request);
        responseObserver.onNext(reservation);
        responseObserver.onCompleted();
    }

    @Override
    public void getAll(Empty request, StreamObserver<ReservationList> responseObserver) {
        GrpcReservationServiceGrpc.GrpcReservationServiceBlockingStub reservationServiceBlockingStub = GrpcReservationServiceGrpc.newBlockingStub(reservationServiceChannel);
        ReservationList reservation = reservationServiceBlockingStub.getAll(request);
        responseObserver.onNext(reservation);
        responseObserver.onCompleted();
    }
}
