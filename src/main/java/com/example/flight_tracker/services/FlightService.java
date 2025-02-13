package com.example.flight_tracker.services;

import com.example.flight_tracker.api.models.flight.FlightInfo;
import com.example.flight_tracker.domain.mongo.SavedFlight;
import com.example.flight_tracker.repositories.mongo.SavedFlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final SavedFlightRepository savedFlightRepository;

    public List<SavedFlight> getAllSavedFlightsByUsername(String email) {
        return savedFlightRepository.findAllByEmail(email);
    }

    public void saveFlight(FlightInfo flightInfo, String email) {

        SavedFlight flight = new SavedFlight();

        flight.setEmail(email);
        flight.setFlightNumber(flightInfo.getFlightNumber());
        flight.setDepIata(flightInfo.getDepIata());
        flight.setArrIata(flightInfo.getArrIata());
        flight.setDepTime(flightInfo.getDepTime());
        flight.setStatus(flightInfo.getStatus());
        flight.setDepActual(flightInfo.getDepActual());
        flight.setArrActual(flightInfo.getArrActual());
        flight.setModel(flightInfo.getModel());

        savedFlightRepository.save(flight);
    }
}
