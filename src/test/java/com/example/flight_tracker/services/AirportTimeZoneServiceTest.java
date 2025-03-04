package com.example.flight_tracker.services;

import com.example.flight_tracker.domain.mysql.AirportTimeZone;
import com.example.flight_tracker.exceptions.ResourceNotFoundException;
import com.example.flight_tracker.repositories.mysql.AirportTimeZoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class AirportTimeZoneServiceTest {

    @InjectMocks
    private AirportTimeZoneService airportTimeZoneService;

    @Mock
    private AirportTimeZoneRepository airportTimeZoneRepository;

    private AirportTimeZone airportTimeZone;

    private static final String IATA_CODE = "FRA";
    private static final String TIMEZONE = "Europe/Paris";

    @BeforeEach
    void setUp() {
        airportTimeZone = new AirportTimeZone();
        airportTimeZone.setIataCode(IATA_CODE);
        airportTimeZone.setTimeZone(TIMEZONE);
    }

    @Test
    void getTimeZone() {
        Mockito.when(airportTimeZoneRepository.findByIataCode(anyString())).thenReturn(Optional.of(airportTimeZone));

        String timezone = airportTimeZoneService.getTimeZone(IATA_CODE);

        assertNotNull(timezone);
        assertEquals(TIMEZONE, timezone);
    }

    @Test
    void getTimeZoneNotFound() {
        Mockito.when(airportTimeZoneRepository.findByIataCode(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> airportTimeZoneService.getTimeZone(IATA_CODE));
    }
}