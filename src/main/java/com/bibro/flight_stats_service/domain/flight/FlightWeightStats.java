package com.bibro.flight_stats_service.domain.flight;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class FlightWeightStats {

    BigDecimal cargoWeight;
    BigDecimal baggageWeight;
    BigDecimal totalWeight;

    public FlightWeightStats(BigDecimal cargoWeight, BigDecimal baggageWeight) {
        this.cargoWeight = cargoWeight;
        this.baggageWeight = baggageWeight;
        this.totalWeight = cargoWeight.add(baggageWeight);
    }
}
