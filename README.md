# flight-stats

# How to run

```
use Java 11

./gradlew clean build
docker-compose build
docker compose up
```

# API
1st functionality

a. Cargo Weight for requested Flight

b. Baggage Weight for requested Flight

c. Total Weight for requested Flight
```
Example request:

http://localhost:8080/weight-details/flightNumber/4133/departureDate/2002-07-28.18:47:00
departureDate = UTC time in format yyyy-MM-dd.HH:mm:ss 

```

2nd functionality

a. Number of flights departing from this airport,

b. Number of flights arriving to this airport,

c. Total number (pieces) of baggage arriving to this airport,

d. Total number (pieces) of baggage departing from this airport.

```
Example request:

http://localhost:8080/airport-details/airportCode/ANC/departureDate/2002-07-28.18:47:00

departureDate = UTC time in format yyyy.MM.dd-HH:mm:ss 

airportCode {
  SEA,
  YYZ,
  YYT,
  ANC,
  LAX,
  MIT,
  LEW,
  GDN,
  KRK,
  PPX
}
```
