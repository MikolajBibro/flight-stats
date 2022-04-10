package com.bibro.flight_stats_service.app.api.flight;

import com.bibro.flight_stats_service.domain.flight.AirportCode;

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

    AirportCode toDomain() {
        return domainValue;
    }
}
