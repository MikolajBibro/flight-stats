package com.bibro.flight_stats_service.infrastructure.mongo.flight;

import com.bibro.flight_stats_service.domain.flight.Weight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreightDto {

    double weight;
    UnitDto unit;
    int pieces;

    public enum UnitDto {
        kg(Weight.Unit.kg),
        lb(Weight.Unit.lb);

        private final Weight.Unit domainValue;

        UnitDto(Weight.Unit domainValue) {
            this.domainValue = domainValue;
        }

        static UnitDto findByDomain(Weight.Unit unit) {
            return null;
        }

        Weight.Unit toDomain() {
            return domainValue;
        }
    }
}
