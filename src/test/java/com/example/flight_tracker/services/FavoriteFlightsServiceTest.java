package com.example.flight_tracker.services;

import com.example.flight_tracker.domain.mongo.FavoriteFlight;
import com.example.flight_tracker.dto.flight.FavoriteFlightInfo;
import com.example.flight_tracker.dto.flight.FlightInfo;
import com.example.flight_tracker.repositories.mongo.FavoriteFlightsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class FavoriteFlightsServiceTest {

    @InjectMocks
    private FavoriteFlightsService favoriteFlightsService;

    @Mock
    private FavoriteFlightsRepository favoriteFlightsRepository;
    
    private FavoriteFlight favoriteFlight;

    private static final String ID = "idstring";
    private static final String MODEL = "Boeing 737";
    private static final String DEP_TIME = "2025-02-17T08:00";
    private static final String ARR_TIME = "2025-02-18T08:00";
    private static final String ON_TIME = "On Time";
    private static final String EMAIL = "email@gmail.com";
    private static final String FLIGHT_NUMBER = "AA123";
    private static final String DEP_IATA = "JFK";
    private static final String ARR_IATA = "LAX";
    private static final String DEP_ACTUAL = "2025-02-17T08:10";
    private static final String ARR_ACTUAL = "2025-02-17T11:00";
    private static final Integer DURATION = 170;

    @BeforeEach
    void setUp() {
        favoriteFlight = new FavoriteFlight();
        favoriteFlight.setEmail(EMAIL);
        favoriteFlight.setModel(MODEL);
        favoriteFlight.setFlightNumber(FLIGHT_NUMBER);
        favoriteFlight.setDepIata(DEP_IATA);
        favoriteFlight.setArrIata(ARR_IATA);
        favoriteFlight.setDepTime(DEP_TIME);
        favoriteFlight.setArrTime(ARR_TIME);
        favoriteFlight.setDepActual(DEP_ACTUAL);
        favoriteFlight.setArrActual(ARR_ACTUAL);
        favoriteFlight.setDuration(DURATION);
        favoriteFlight.setStatus(ON_TIME);
    }

    @Test
    void getAllFlightsByEmail() {
        List<FavoriteFlight> favoriteFlights = new ArrayList<>();
        favoriteFlights.add(favoriteFlight);

        Mockito.when(favoriteFlightsRepository.findAllByEmail(anyString())).thenReturn(favoriteFlights);

        List<FavoriteFlightInfo> foundFavoriteFlights = favoriteFlightsService.getAllFlightsByEmail(EMAIL);

        Assertions.assertEquals(favoriteFlights.size(), foundFavoriteFlights.size());
        Mockito.verify(favoriteFlightsRepository, times(1)).findAllByEmail(anyString());
    }

    @Test
    void deleteFlightById() {
        favoriteFlightsService.deleteFlightById(ID);

        Mockito.verify(favoriteFlightsRepository, times(1)).deleteById(anyString());
    }

    @Test
    void saveFlight() {
        FlightInfo flightInfo = new FlightInfo();
        flightInfo.setModel(MODEL);
        flightInfo.setFlightNumber(FLIGHT_NUMBER);
        flightInfo.setDepIata(DEP_IATA);
        flightInfo.setArrIata(ARR_IATA);
        flightInfo.setDepTime(DEP_TIME);
        flightInfo.setArrTime(ARR_TIME);
        flightInfo.setDepActual(DEP_ACTUAL);
        flightInfo.setArrActual(ARR_ACTUAL);
        flightInfo.setDuration(DURATION);
        flightInfo.setStatus(ON_TIME);

        favoriteFlight.setId(ID);

        Mockito.when(favoriteFlightsRepository.save(any())).thenReturn(favoriteFlight);

        FavoriteFlight savedFavoriteFlight = favoriteFlightsService.saveFlight(flightInfo, EMAIL);

        Assertions.assertSame(favoriteFlight.getId(), savedFavoriteFlight.getId());
        Assertions.assertSame(favoriteFlight.getEmail(), savedFavoriteFlight.getEmail());
        Mockito.verify(favoriteFlightsRepository, times(1)).save(any());
    }
}