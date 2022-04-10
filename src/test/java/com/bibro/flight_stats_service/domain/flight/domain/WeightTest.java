package com.bibro.flight_stats_service.domain.flight.domain;

import com.bibro.flight_stats_service.domain.flight.Weight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class WeightTest {

    @Test
    public void shouldCorrectlyConvertWeightToKg() {
        Weight weight = new Weight(BigDecimal.valueOf(100), Weight.Unit.lb);
        Assertions.assertEquals(0, BigDecimal.valueOf(45).compareTo(weight.toKg()));
    }
}
