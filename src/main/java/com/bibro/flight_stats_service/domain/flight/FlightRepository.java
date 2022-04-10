package com.bibro.flight_stats_service.domain.flight;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

public interface FlightRepository {

    Mono<Flight> findByFlightNumberAndDepartureDate(int flightNumber, ZonedDateTime dateTime);

    Flux<Flight> findByDepartingAirportAndDepartureDate(AirportCode airportCode, ZonedDateTime dateTime);

    Flux<Flight> findByArrivalAirportAndDepartureDate(AirportCode airportCode, ZonedDateTime dateTime);
}
