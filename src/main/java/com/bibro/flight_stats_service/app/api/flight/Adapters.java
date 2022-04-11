package com.bibro.flight_stats_service.app.api.flight;

import com.bibro.flight_stats_service.domain.flight.AirportStats;
import com.bibro.flight_stats_service.domain.flight.FlightWeightStats;

public class Adapters {

    static FlightWeightStatsDto toDto(FlightWeightStats details) {
        return new FlightWeightStatsDto(details.getCargoWeight(), details.getBaggageWeight(), details.getTotalWeight());
    }

    static AirportStatsDto toDto(AirportStats details) {
        return new AirportStatsDto(details.getNumberOfDepartingFlights(),
                details.getTotalDepartingBaggagePieces(),
                details.getNumberOfArrivingFlights(),
                details.getTotalArrivingBaggagePieces());
    }
}
