package com.bibro.flight_stats_service.infrastructure.mongo.flight;

import com.bibro.flight_stats_service.domain.flight.Flight;
import com.bibro.flight_stats_service.domain.flight.Freight;
import com.bibro.flight_stats_service.domain.flight.Weight;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Adapters {

    static Flight toDomain(MongoFlight dto) {
        return new Flight(dto.getFlightNumber(),
                dto.departureAirport.toDomain(),
                dto.arrivalAirport.toDomain(),
                toDomain(dto.departureDate),
                toDomain(dto.cargos, dto.baggage));
    }

    private static List<Freight> toDomain(List<MongoFreight> cargos, List<MongoFreight> baggage) {
        return Stream.concat(cargos.stream().map(Adapters::cargoToDomain), baggage.stream().map(Adapters::baggageToDomain))
                .collect(Collectors.toList());
    }

    private static Freight cargoToDomain(MongoFreight mongoFreight) {
        return new Freight(new Weight(BigDecimal.valueOf(mongoFreight.weight), mongoFreight.getUnit().toDomain()),
                mongoFreight.getPieces(),
                Freight.Type.CARGO);
    }

    private static Freight baggageToDomain(MongoFreight mongoFreight) {
        return new Freight(new Weight(BigDecimal.valueOf(mongoFreight.weight), mongoFreight.getUnit().toDomain()),
                mongoFreight.getPieces(),
                Freight.Type.BAGGAGE);
    }

    private static LocalDateTime toDomain(ZonedDateTime date) {
        return date.withZoneSameInstant(Clock.systemDefaultZone().getZone()).toLocalDateTime();
    }
}
