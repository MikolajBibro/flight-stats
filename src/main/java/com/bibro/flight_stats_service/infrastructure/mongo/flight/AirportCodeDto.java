package com.bibro.flight_stats_service.infrastructure.mongo.flight;

import com.bibro.flight_stats_service.domain.flight.AirportCode;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum AirportCodeDto {

    SEA(AirportCode.SEA),
    YYZ(AirportCode.YYZ),
    YYT(AirportCode.YYT),
    ANC(AirportCode.ANC),
    LAX(AirportCode.LAX),
    MIT(AirportCode.MIT),
    LEW(AirportCode.LEW),
    GDN(AirportCode.GDN),
    KRK(AirportCode.KRK),
    PPX(AirportCode.PPX);

    private final AirportCode domainValue;

    AirportCodeDto(AirportCode domainValue) {
        this.domainValue = domainValue;
    }

    static AirportCodeDto findByDomain(AirportCode airportCode) {
        return Arrays.stream(values())
                .filter(dto -> dto.toDomain().equals(airportCode))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    AirportCode toDomain() {
        return domainValue;
    }
}
