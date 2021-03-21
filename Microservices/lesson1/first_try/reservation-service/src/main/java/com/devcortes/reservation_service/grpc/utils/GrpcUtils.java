package com.devcortes.reservation_service.grpc.utils;

import com.google.protobuf.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class GrpcUtils {

    public Timestamp getTimestamp(LocalDate date) {
        if (date == null) {
            return Timestamp.newBuilder().clear().build();
        }
        Instant instant = date.atStartOfDay().toInstant(ZoneOffset.UTC);
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }

    public Timestamp getTimestamp(LocalDateTime date) {
        if (date == null) {
            return Timestamp.newBuilder().clear().build();
        }
        Instant instant = date.toInstant(ZoneOffset.UTC);
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
