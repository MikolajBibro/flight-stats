package com.bibro.flight_stats_service.app.api.flight;

import com.bibro.flight_stats_service.domain.flight.AirportCode;
import com.bibro.flight_stats_service.domain.flight.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class FlightHandler {

    FlightService flightService;

    @Autowired
    public FlightHandler(FlightService flightService) {
        this.flightService = flightService;
    }

    Mono<ServerResponse> getFlightWeightDetails(ServerRequest request) {
        int flightNumber = Integer.parseInt(request.pathVariable("flightNumber"));
        LocalDateTime departureDate = toLocalDateTime(request.pathVariable("departureDate"));

        return flightService
                .getFlightWeightDetails(flightNumber, departureDate)
                .flatMap(r -> ok().bodyValue(Adapters.toDto(r)))
                .switchIfEmpty(notFound().build());
    }

    Mono<ServerResponse> getAirportDetails(ServerRequest request) {
        AirportCode airportCode = AirportCodeDto.valueOf(request.pathVariable("airportCode")).toDomain();
        LocalDateTime departureDate = toLocalDateTime(request.pathVariable("departureDate"));

        return flightService
                .getAirportDetails(airportCode, departureDate)
                .flatMap(r -> ok().bodyValue(Adapters.toDto(r)))
                .switchIfEmpty(notFound().build());
    }

    private LocalDateTime toLocalDateTime(String localDateTime) {
        return LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
    }
}
