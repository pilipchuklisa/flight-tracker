package com.example.flight_tracker.services;

import com.example.flight_tracker.exceptions.ResourceNotFoundException;
import com.example.flight_tracker.repositories.mysql.AirportTimeZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportTimeZoneService {

    private final AirportTimeZoneRepository airportTimeZoneRepository;

    public String getTimeZone(String iataCode) {
        return airportTimeZoneRepository.findByIataCode(iataCode).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found")).getTimeZone();
    }
}
