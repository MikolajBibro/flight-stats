package com.bibro.flight_stats_service.domain.flight.api;

import com.bibro.flight_stats_service.app.api.flight.AirportCodeDto;
import com.bibro.flight_stats_service.app.api.flight.FlightDetailsDto;
import com.bibro.flight_stats_service.app.api.flight.FlightWeightDetailsDto;
import com.bibro.flight_stats_service.infrastructure.mongo.flight.MongoAirportCode;
import com.bibro.flight_stats_service.infrastructure.mongo.flight.MongoFlight;
import com.bibro.flight_stats_service.infrastructure.mongo.flight.MongoFreight;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class FlightApiTest {

    @Container
    public static MongoDBContainer container =
            new MongoDBContainer(DockerImageName.parse("mongo:4.4.3"));
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private ReactiveMongoTemplate template;

    @DynamicPropertySource
    static void registerMongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl);
    }

    @BeforeEach
    void initAll() {
        container.start();
    }

    @AfterEach
    void removeData() {
        template.remove(new Query(new Criteria()), MongoFlight.class).block();
    }

    @Test
    void shouldReturnCorrectWeightDetails() {
        saveAll(flight(1, MongoAirportCode.KRK, MongoAirportCode.GDN, LocalDateTime.of(2012, 12, 12, 12, 12)));

        webTestClient
                .get()
                .uri("weight-details/flightNumber/{flightNumber}/departureDate/{departureDate}", 1, "2012-12-12.12:12:00")
                .exchange()
                .expectBody((FlightWeightDetailsDto.class))
                .isEqualTo(
                        new FlightWeightDetailsDto(
                                BigDecimal.valueOf(5.0), BigDecimal.valueOf(24.0), BigDecimal.valueOf(29.0)));
    }

    @Test
    void shouldReturnCorrectFlightDetails() {
        saveAll(flight(1, MongoAirportCode.ANC, MongoAirportCode.GDN, LocalDateTime.of(2012, 12, 12, 12, 12)),
                flight(2, MongoAirportCode.GDN, MongoAirportCode.ANC, LocalDateTime.of(2012, 12, 12, 12, 12)),
                flight(2, MongoAirportCode.GDN, MongoAirportCode.ANC, LocalDateTime.of(2012, 12, 12, 12, 12)));

        webTestClient
                .get()
                .uri("airport-details/airportCode/{airportCode}/departureDate/{departureDate}", AirportCodeDto.ANC, "2012-12-12.12:12:00")
                .exchange()
                .expectBody((FlightDetailsDto.class))
                .isEqualTo(new FlightDetailsDto(1, 2, 2, 4));
    }

    MongoFlight flight(
            int flightNumber,
            MongoAirportCode departure,
            MongoAirportCode destination,
            LocalDateTime date) {
        MongoFreight f1 = new MongoFreight(5.0, MongoFreight.UnitDto.kg, 10);
        MongoFreight f2 = new MongoFreight(12.0, MongoFreight.UnitDto.kg, 5);
        MongoFreight f3 = new MongoFreight(12.0, MongoFreight.UnitDto.kg, 11);
        ZonedDateTime departureDate = ZonedDateTime.of(date, ZoneId.of("UTC"));
        return new MongoFlight(
                flightNumber,
                departure,
                destination,
                departureDate,
                Collections.singletonList(f1),
                Arrays.asList(f2, f3));
    }

    private void saveAll(MongoFlight... flights) {
        template.insertAll(Arrays.asList(flights)).blockLast();
    }
}
