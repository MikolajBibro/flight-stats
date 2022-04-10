package com.bibro.flight_stats_service.domain.flight;

import lombok.Value;

@Value
public class AirportDetails {

    int numberOfDepartingFlights;
    int totalDepartingBaggagePieces;
    int numberOfArrivingFlights;
    int totalArrivingBaggagePieces;
}
