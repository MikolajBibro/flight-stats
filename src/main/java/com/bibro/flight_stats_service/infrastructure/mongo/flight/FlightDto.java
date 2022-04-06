package com.bibro.flight_stats_service.infrastructure.mongo.flight;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("flights")
public class FlightDto {

    int id;
    int flightId;
    int flightNumber;
    AirportCodeDto departureAirport;
    AirportCodeDto arrivalAirport;
    long departureDate;
    List<FreightDto> cargos;
    List<FreightDto> baggage;
}
