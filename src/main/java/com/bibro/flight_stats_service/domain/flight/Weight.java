package com.bibro.flight_stats_service.domain.flight;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Weight {

    private static double lbToKg = 0.45;

    BigDecimal value;
    Unit unit;

    public BigDecimal toKg() {
        return unit.equals(Unit.kg) ? value : value.multiply(BigDecimal.valueOf(lbToKg));
    }

    public enum Unit {
        kg,
        lb
    }
}
