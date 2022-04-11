package com.bibro.flight_stats_service.domain.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Mono<FlightWeightStats> getFlightWeightStats(int flightNumber, LocalDateTime departureDate) {
        return flightRepository
                .findByFlightNumberAndDepartureDate(flightNumber, departureDate)
                .map(Flight::getWeightDetails);
    }

    public Mono<AirportStats> getAirportStats(AirportCode code, LocalDateTime departureDate) {
        Flux<Flight> departures = flightRepository.findByDepartingAirportAndDepartureDate(code, departureDate);
        Flux<Flight> arrivals = flightRepository.findByArrivalAirportAndDepartureDate(code, departureDate);

        return Mono.zip(departures.collectList(), arrivals.collectList(), this::createAirportStats);
    }

    private AirportStats createAirportStats(List<Flight> departures, List<Flight> arrivals) {
        return new AirportStats(
                departures.size(),
                getBaggagePieces(departures),
                arrivals.size(),
                getBaggagePieces(arrivals));
    }

    private int getBaggagePieces(List<Flight> flights) {
        return flights.stream()
                .map(Flight::getFreights)
                .flatMap(Collection::stream)
                .filter(f -> f.getFreightType().equals(Freight.Type.BAGGAGE))
                .map(Freight::getPieces)
                .reduce(0, Integer::sum);
    }
}
