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

import java.time.LocalDateTime;

@Repository
public class FlightMongoRepository implements FlightRepository {

    ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public FlightMongoRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<Flight> findByFlightNumberAndDepartureDate(int flightNumber, LocalDateTime dateTime) {
        Query query = new Query();
        query.addCriteria(Criteria.where("flightNumber").is(flightNumber));
        query.addCriteria(Criteria.where("departureDate").is(dateTime));

        return mongoTemplate.findOne(query, MongoFlight.class).map(Adapters::toDomain);
    }

    @Override
    public Flux<Flight> findByDepartingAirportAndDepartureDate(AirportCode airportCode, LocalDateTime dateTime) {
        MongoAirportCode code = MongoAirportCode.findByDomain(airportCode);

        Query query = new Query();
        query.addCriteria(Criteria.where("departureAirport").is(code));
        query.addCriteria(Criteria.where("departureDate").is(dateTime));

        return mongoTemplate.find(query, MongoFlight.class).map(Adapters::toDomain);
    }

    @Override
    public Flux<Flight> findByArrivalAirportAndDepartureDate(AirportCode airportCode, LocalDateTime dateTime) {
        MongoAirportCode code = MongoAirportCode.findByDomain(airportCode);

        Query query = new Query();
        query.addCriteria(Criteria.where("arrivalAirport").is(code));
        query.addCriteria(Criteria.where("departureDate").is(dateTime));

        return mongoTemplate.find(query, MongoFlight.class).map(Adapters::toDomain);
    }
}
