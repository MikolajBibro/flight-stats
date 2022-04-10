package com.bibro.flight_stats_service.domain.flight;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Log4j2
@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Mono<FlightWeightDetails> getFlightWeightDetails(int flightNumber, LocalDateTime departureDate) {
        return flightRepository
                .findByFlightNumberAndDepartureDate(flightNumber, departureDate)
                .map(Flight::getWeightDetails);
    }

    public Mono<AirportDetails> getAirportDetails(AirportCode code, LocalDateTime departureDate) {
        Flux<Flight> departures = flightRepository.findByDepartingAirportAndDepartureDate(code, departureDate);
        Flux<Flight> arrivals = flightRepository.findByArrivalAirportAndDepartureDate(code, departureDate);

        return Mono.zip(departures.collectList(), arrivals.collectList(), this::createFlightDetails);
    }

    private AirportDetails createFlightDetails(List<Flight> departures, List<Flight> arrivals) {
        return new AirportDetails(
                departures.size(),
                getBaggagePieces(departures),
                arrivals.size(),
                getBaggagePieces(arrivals));
    }

    private int getBaggagePieces(List<Flight> flights) {
        return (int)
                flights.stream()
                        .map(Flight::getFreights)
                        .flatMap(Collection::stream)
                        .filter(f -> f.getFreightType().equals(Freight.Type.BAGGAGE))
                        .count();
    }
}
