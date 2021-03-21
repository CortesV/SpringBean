package com.devcortes.reservationclient.server.utils;

import com.google.protobuf.Timestamp;
import org.springframework.stereotype.Component;

import java.time.*;

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

    public LocalDate toLocalDate(Timestamp timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos()), ZoneId.of("UTC"))
                .toLocalDate();
    }

    public LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos()), ZoneId.of("UTC"));
    }
}
