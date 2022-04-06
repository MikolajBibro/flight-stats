package com.bibro.flight_stats_service.domain.flight;

import lombok.Value;

@Value
public class Freight {

    Weight weight;
    int pieces;
    Type freightType;

    public enum Type {
        CARGO, BAGGAGE
    }
}
