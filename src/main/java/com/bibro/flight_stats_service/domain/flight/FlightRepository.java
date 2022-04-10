package com.bibro.flight_stats_service.domain.flight;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface FlightRepository {

    Mono<Flight> findByFlightNumberAndDepartureDate(int flightNumber, LocalDateTime dateTime);

    Flux<Flight> findByDepartingAirportAndDepartureDate(AirportCode airportCode, LocalDateTime dateTime);

    Flux<Flight> findByArrivalAirportAndDepartureDate(AirportCode airportCode, LocalDateTime dateTime);
}
