package com.bibro.flight_stats_service.domain.flight;

import lombok.Value;

@Value
public class Weight {

    double value;
    Unit unit;

    public double toKg() {
        return 0.0;
    }

    public enum Unit {
        kg,
        lb
    }
}
