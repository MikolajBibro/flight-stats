package com.bibro.flight_stats_service.domain.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    int flightNumber;
    AirportCode departureAirport;
    AirportCode arrivalAirport;
    LocalDateTime departureDate;
    List<Freight> freights;

    public FlightWeightDetails getWeightDetails() {
        return new FlightWeightDetails(getCargoWeight(), getBaggageWeight());
    }

    private BigDecimal getCargoWeight() {
        Predicate<Freight> freightTypeFilter = f -> f.getFreightType().equals(Freight.Type.CARGO);
        return calculateWeight(freightTypeFilter);
    }

    private BigDecimal getBaggageWeight() {
        Predicate<Freight> freightTypeFilter = f -> f.getFreightType().equals(Freight.Type.BAGGAGE);
        return calculateWeight(freightTypeFilter);
    }

    private BigDecimal calculateWeight(Predicate<Freight> freightFilter) {
        return freights.stream()
                .filter(freightFilter)
                .map(f -> f.getWeight().toKg())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
