package com.bibro.flight_stats_service.domain.flight;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class FlightWeightDetails {

    BigDecimal cargoWeight;
    BigDecimal baggageWeight;
    BigDecimal totalWeight;

    public FlightWeightDetails(BigDecimal cargoWeight, BigDecimal baggageWeight) {
        this.cargoWeight = cargoWeight;
        this.baggageWeight = baggageWeight;
        this.totalWeight = cargoWeight.add(baggageWeight);
    }
}
