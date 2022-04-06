package com.bibro.flight_stats_service.infrastructure.mongo.flight;

import com.bibro.flight_stats_service.domain.flight.Flight;
import com.bibro.flight_stats_service.domain.flight.Freight;
import com.bibro.flight_stats_service.domain.flight.Weight;

import java.time.Instant;
import java.util.Collections;

class Adapters {

    static Flight toDomain(FlightDto dto) {
        return new Flight(dto.getFlightId(),
                dto.getFlightNumber(),
                dto.departureAirport.toDomain(),
                dto.arrivalAirport.toDomain(),
                Instant.ofEpochMilli(dto.departureDate),
                Collections.emptyList());
    }

    private static Freight cargoToDomain(FreightDto freightDto) {
        return new Freight(new Weight(freightDto.weight, freightDto.getUnit().toDomain()), freightDto.getPieces(), Freight.Type.CARGO);
    }

    private static Freight baggageToDomain(FreightDto freightDto) {
        return new Freight(new Weight(freightDto.weight, freightDto.getUnit().toDomain()), freightDto.getPieces(), Freight.Type.BAGGAGE);
    }
}
