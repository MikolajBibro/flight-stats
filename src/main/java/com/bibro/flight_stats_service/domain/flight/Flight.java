package com.bibro.flight_stats_service.domain.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("flight2t")
public class Flight {

  int flightId;
  int flightNumber;
  AirportCode departureAirport;
  AirportCode arrivalAirport;
  Instant departureDate;
  List<Freight> freights;
}
