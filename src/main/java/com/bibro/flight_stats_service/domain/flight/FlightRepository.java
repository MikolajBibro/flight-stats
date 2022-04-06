package com.bibro.flight_stats_service.domain.flight;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;


public interface FlightRepository {

    Mono<Flight> findByFlightNumberAndDepartureDate(int flightNumber, Instant date);

    Flux<Flight> findByDepartureAirportAndDepartureDate(AirportCode airportCode, Instant date);

    Flux<Flight> findByArrivalAirportAndDepartureDate(AirportCode airportCode, Instant date);
}
