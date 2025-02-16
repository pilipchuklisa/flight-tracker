package com.example.flight_tracker.services;

import com.example.flight_tracker.dto.flight.FlightInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    public List<FlightInfo> searchFlights(String flightNumber, String depIata, String arrIata, String depTime) {

        System.out.println(flightNumber);
        System.out.println(depIata);
        System.out.println(arrIata);
        System.out.println(depTime);

        FlightInfo favoriteFlight1 = new FlightInfo();
        favoriteFlight1.setModel("Boeing 737");
        favoriteFlight1.setDepTime("2025-02-17T08:00");
        favoriteFlight1.setStatus("On Time");
        favoriteFlight1.setFlightNumber("AA123");
        favoriteFlight1.setDepIata("JFK");
        favoriteFlight1.setArrIata("LAX");
        favoriteFlight1.setDepActual("2025-02-17T08:10");
        favoriteFlight1.setArrActual("2025-02-17T11:00");

        FlightInfo favoriteFlight2 = new FlightInfo();
        favoriteFlight2.setModel("Airbus A320");
        favoriteFlight2.setDepTime("2025-02-18T14:30");
        favoriteFlight2.setStatus("Delayed");
        favoriteFlight2.setFlightNumber("BA456");
        favoriteFlight2.setDepIata("LHR");
        favoriteFlight2.setArrIata("CDG");
        favoriteFlight2.setDepActual("2025-02-18T15:00");
        favoriteFlight2.setArrActual("2025-02-18T17:30");

        List<FlightInfo> flights = new ArrayList<>(List.of(favoriteFlight1, favoriteFlight2));

        return flights;
    }
}
