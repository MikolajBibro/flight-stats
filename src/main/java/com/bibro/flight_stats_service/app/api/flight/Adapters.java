package com.bibro.flight_stats_service.app.api.flight;

import com.bibro.flight_stats_service.domain.flight.AirportDetails;
import com.bibro.flight_stats_service.domain.flight.FlightWeightDetails;

public class Adapters {

    static FlightWeightDetailsDto toDto(FlightWeightDetails details) {
        return new FlightWeightDetailsDto(details.getCargoWeight(), details.getBaggageWeight(), details.getTotalWeight());
    }

    static FlightDetailsDto toDto(AirportDetails details) {
        return new FlightDetailsDto(details.getNumberOfDepartingFlights(),
                details.getTotalDepartingBaggagePieces(),
                details.getNumberOfArrivingFlights(),
                details.getTotalArrivingBaggagePieces());
    }
}
