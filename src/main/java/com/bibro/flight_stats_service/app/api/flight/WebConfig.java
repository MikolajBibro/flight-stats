package com.bibro.flight_stats_service.app.api.flight;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
public class WebConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(FlightHandler flightHandler) {
        return route(
                GET("weight-stats/flightNumber/{flightNumber}/departureDate/{departureDate}"),
                flightHandler::getFlightWeightStats)
                .andRoute(
                        GET("airport-stats/airportCode/{airportCode}/departureDate/{departureDate}"),
                        flightHandler::getAirportStats);
    }
}
