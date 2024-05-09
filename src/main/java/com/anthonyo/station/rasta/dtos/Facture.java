package com.anthonyo.station.rasta.dtos;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record Facture(
        LocalDateTime dateTime ,
        String name,
        String type,
        Double quantity
) {

}
