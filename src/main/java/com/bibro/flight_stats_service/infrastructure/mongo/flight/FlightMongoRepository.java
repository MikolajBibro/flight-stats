package com.bibro.flight_stats_service.infrastructure.mongo.flight;


import com.bibro.flight_stats_service.domain.flight.AirportCode;
import com.bibro.flight_stats_service.domain.flight.Flight;
import com.bibro.flight_stats_service.domain.flight.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Repository
public class FlightMongoRepository implements FlightRepository {

    @Autowired
    ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Flight> findByFlightNumberAndDepartureDate(int flightNumber, Instant date) {
        Query query = new Query();
        query.addCriteria(Criteria.where("flightNumber").is(flightNumber));

        return mongoTemplate.findOne(query, FlightDto.class)
                .map(Adapters::toDomain);
    }

    @Override
    public Flux<Flight> findByDepartureAirportAndDepartureDate(AirportCode airportCode, Instant date) {
        AirportCodeDto code = AirportCodeDto.findByDomain(airportCode);

        Query query = new Query();
        query.addCriteria(Criteria.where("departureAirport").is(code));

        return mongoTemplate.find(query, FlightDto.class)
                .map(Adapters::toDomain);
    }

    @Override
    public Flux<Flight> findByArrivalAirportAndDepartureDate(AirportCode airportCode, Instant date) {
        AirportCodeDto code = AirportCodeDto.findByDomain(airportCode);

        Query query = new Query();
        query.addCriteria(Criteria.where("arrivalAirport").is(code));

        return mongoTemplate.find(query, FlightDto.class)
                .map(Adapters::toDomain);
    }
}
