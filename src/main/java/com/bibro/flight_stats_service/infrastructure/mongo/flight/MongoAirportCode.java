package com.bibro.flight_stats_service.infrastructure.mongo.flight;

import com.bibro.flight_stats_service.domain.flight.AirportCode;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum MongoAirportCode {

    ANC(AirportCode.ANC),
    GDN(AirportCode.GDN),
    KRK(AirportCode.KRK),
    LAX(AirportCode.LAX),
    LEW(AirportCode.LEW),
    MIT(AirportCode.MIT),
    PPX(AirportCode.PPX),
    SEA(AirportCode.SEA),
    YYT(AirportCode.YYT),
    YYZ(AirportCode.YYZ);

    private final AirportCode domainValue;

    MongoAirportCode(AirportCode domainValue) {
        this.domainValue = domainValue;
    }

    static MongoAirportCode findByDomain(AirportCode airportCode) {
        return Arrays.stream(values())
                .filter(dto -> dto.toDomain().equals(airportCode))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    AirportCode toDomain() {
        return domainValue;
    }
}
