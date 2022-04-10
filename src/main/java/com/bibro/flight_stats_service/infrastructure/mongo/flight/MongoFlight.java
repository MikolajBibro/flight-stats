package com.bibro.flight_stats_service.infrastructure.mongo.flight;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("flights")
public class MongoFlight {

    int flightNumber;
    MongoAirportCode departureAirport;
    MongoAirportCode arrivalAirport;
    LocalDateTime departureDate;
    List<MongoFreight> cargos;
    List<MongoFreight> baggage;
}
