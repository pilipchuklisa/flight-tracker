package com.example.flight_tracker.services;

import com.example.flight_tracker.domain.mongo.FavoriteFlight;
import com.example.flight_tracker.dto.flight.FavoriteFlightDto;
import com.example.flight_tracker.dto.flight.FlightDto;
import com.example.flight_tracker.mapper.FavoriteFlightMapperImpl;
import com.example.flight_tracker.repositories.mongo.FavoriteFlightsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
        favoriteFlightsService = new FavoriteFlightsService(favoriteFlightsRepository, new FavoriteFlightMapperImpl());

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

        List<FavoriteFlightDto> foundFavoriteFlights = favoriteFlightsService.getAllFlightsByEmail(EMAIL);

        Assertions.assertEquals(favoriteFlights.size(), foundFavoriteFlights.size());
        Mockito.verify(favoriteFlightsRepository, times(1)).findAllByEmail(anyString());
    }

    @Test
    void deleteFlightById() {
        favoriteFlightsService.deleteFlightById(ID);

        Mockito.verify(favoriteFlightsRepository, times(1)).deleteById(anyString());
    }

    @Test
    void saveIfNotExistFlightNotExistTest() {
        FlightDto flightDto = new FlightDto();
        flightDto.setModel(MODEL);
        flightDto.setFlightNumber(FLIGHT_NUMBER);
        flightDto.setDepIata(DEP_IATA);
        flightDto.setArrIata(ARR_IATA);
        flightDto.setDepTime(DEP_TIME);
        flightDto.setArrTime(ARR_TIME);
        flightDto.setDepActual(DEP_ACTUAL);
        flightDto.setArrActual(ARR_ACTUAL);
        flightDto.setDuration(DURATION);
        flightDto.setStatus(ON_TIME);

        favoriteFlight.setId(ID);

        Mockito.when(favoriteFlightsRepository.findAllByEmail(anyString())).thenReturn(new ArrayList<>());
        Mockito.when(favoriteFlightsRepository.save(any())).thenReturn(favoriteFlight);

        FavoriteFlight savedFavoriteFlight = favoriteFlightsService.saveIfNotExist(flightDto, EMAIL);

        Assertions.assertSame(favoriteFlight.getId(), savedFavoriteFlight.getId());
        Assertions.assertSame(favoriteFlight.getEmail(), savedFavoriteFlight.getEmail());
        Mockito.verify(favoriteFlightsRepository, times(1)).save(any());
    }

    @Test
    void saveIfNotExistFlightAlreadyExistTest() {
        FlightDto flightDto = new FlightDto();
        flightDto.setModel(MODEL);
        flightDto.setFlightNumber(FLIGHT_NUMBER);
        flightDto.setDepIata(DEP_IATA);
        flightDto.setArrIata(ARR_IATA);
        flightDto.setDepTime(DEP_TIME);
        flightDto.setArrTime(ARR_TIME);
        flightDto.setDepActual(DEP_ACTUAL);
        flightDto.setArrActual(ARR_ACTUAL);
        flightDto.setDuration(DURATION);
        flightDto.setStatus(ON_TIME);

        favoriteFlight.setId(ID);

        Mockito.when(favoriteFlightsRepository.findAllByEmail(anyString())).thenReturn(List.of(favoriteFlight));

        FavoriteFlight savedFavoriteFlight = favoriteFlightsService.saveIfNotExist(flightDto, EMAIL);

        Assertions.assertNull(savedFavoriteFlight);
        Mockito.verify(favoriteFlightsRepository, times(0)).save(any());
    }
}