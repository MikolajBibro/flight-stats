package com.bibro.flight_stats_service.app.api.flight;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class FlightWeightDetailsDto {

    BigDecimal cargoWeight;
    BigDecimal baggageWeight;
    BigDecimal totalWeight;
}
