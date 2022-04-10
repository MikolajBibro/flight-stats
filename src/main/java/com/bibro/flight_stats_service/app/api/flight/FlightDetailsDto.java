package com.bibro.flight_stats_service.app.api.flight;

import lombok.Value;

@Value
public class FlightDetailsDto {

    int numberOfDepartingFlights;
    int totalDepartingBaggagePieces;
    int numberOfArrivingFlights;
    int totalArrivingBaggagePieces;
}
