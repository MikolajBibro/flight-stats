package com.bibro.flight_stats_service.domain.flight;

import com.bibro.flight_stats_service.infrastructure.mongo.flight.FlightMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.function.Predicate;

@Service
public class FlightService {

    FlightMongoRepository flightMongoRepository;

    @Autowired
    public FlightService(FlightMongoRepository flightMongoRepository) {
        this.flightMongoRepository = flightMongoRepository;
    }

    public Mono<Double> calculateCargoWeight(int flightNumber, Instant date) {
        Predicate<Freight> freightTypePredicate = f -> f.getFreightType().equals(Freight.Type.CARGO);
        return calculateWeight(flightNumber, date, freightTypePredicate);
    }

    public Mono<Double> calculateBaggageWeight(int flightNumber, Instant date) {
        Predicate<Freight> freightTypePredicate = f -> f.getFreightType().equals(Freight.Type.BAGGAGE);
        return calculateWeight(flightNumber, date, freightTypePredicate);
    }

    public Mono<Double> calculateTotalWeight(int flightNumber, Instant date) {
        return calculateWeight(flightNumber, date, f -> true);
    }

    private Mono<Double> calculateWeight(int flightNumber, Instant date, Predicate<Freight> freightTypePredicate) {
        return flightMongoRepository.findByFlightNumberAndDepartureDate(flightNumber, date)
                .map(Flight::getFreights)
                .flatMapMany(Flux::fromIterable)
                .filter(freightTypePredicate)
                .map(Freight::getWeight)
                .reduce(0.0, (result, w) -> result + w.toKg());
    }
}
