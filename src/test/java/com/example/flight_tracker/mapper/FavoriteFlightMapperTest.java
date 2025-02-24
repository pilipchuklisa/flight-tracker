package com.example.flight_tracker.mapper;

import com.example.flight_tracker.domain.mongo.FavoriteFlight;
import com.example.flight_tracker.dto.flight.FavoriteFlightDto;
import com.example.flight_tracker.dto.flight.FlightDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FavoriteFlightMapperTest {

    private FavoriteFlightMapper favoriteFlightMapper;

    private static final String MODEL = "Boeing 737";
    private static final String FLIGHT_NUMBER = "AA123";
    private static final String DEP_IATA = "JFK";
    private static final String DEP_NAME = "John F. Kennedy International";
    private static final String DEP_COUNTRY = "USA";
    private static final String DEP_CITY = "New York";
    private static final String ARR_IATA = "LAX";
    private static final String ARR_NAME = "Los Angeles International";
    private static final String ARR_COUNTRY = "USA";
    private static final String ARR_CITY = "Los Angeles";
    private static final String DEP_TIME = "2025-02-17T08:00";
    private static final String DEP_TIME_UTC = "2025-02-17T13:00:00Z";
    private static final String ARR_TIME = "2025-02-18T08:00";
    private static final String ARR_TIME_UTC = "2025-02-18T15:00:00Z";
    private static final String DEP_ACTUAL = "2025-02-17T08:10";
    private static final String DEP_ACTUAL_UTC = "2025-02-17T13:10:00Z";
    private static final String ARR_ACTUAL = "2025-02-17T11:00";
    private static final String ARR_ACTUAL_UTC = "2025-02-17T18:00:00Z";
    private static final Integer DURATION = 170;
    private static final Integer DEP_DELAYED = 10;
    private static final Integer ARR_DELAYED = 15;
    private static final String STATUS = "On Time";

    @BeforeEach
    void setUp() {
        favoriteFlightMapper = new FavoriteFlightMapperImpl();
    }

    @Test
    void flightDtoToFavoriteFlight() {
        FlightDto flightDto = getFlightDto();

        FavoriteFlight favoriteFlight = favoriteFlightMapper.flightDtoToFavoriteFlight(flightDto);

        assertNotNull(favoriteFlight);
        assertEquals(MODEL, favoriteFlight.getModel());
        assertEquals(FLIGHT_NUMBER, favoriteFlight.getFlightNumber());
        assertEquals(DEP_IATA, favoriteFlight.getDepIata());
        assertEquals(DEP_NAME, favoriteFlight.getDepName());
        assertEquals(DEP_COUNTRY, favoriteFlight.getDepCountry());
        assertEquals(DEP_CITY, favoriteFlight.getDepCity());
        assertEquals(ARR_IATA, favoriteFlight.getArrIata());
        assertEquals(ARR_NAME, favoriteFlight.getArrName());
        assertEquals(ARR_COUNTRY, favoriteFlight.getArrCountry());
        assertEquals(ARR_CITY, favoriteFlight.getArrCity());
        assertEquals(DEP_TIME, favoriteFlight.getDepTime());
        assertEquals(DEP_TIME_UTC, favoriteFlight.getDepTimeUtc());
        assertEquals(ARR_TIME, favoriteFlight.getArrTime());
        assertEquals(ARR_TIME_UTC, favoriteFlight.getArrTimeUtc());
        assertEquals(DEP_ACTUAL, favoriteFlight.getDepActual());
        assertEquals(DEP_ACTUAL_UTC, favoriteFlight.getDepActualUtc());
        assertEquals(ARR_ACTUAL, favoriteFlight.getArrActual());
        assertEquals(ARR_ACTUAL_UTC, favoriteFlight.getArrActualUtc());
        assertEquals(DURATION, favoriteFlight.getDuration());
        assertEquals(DEP_DELAYED, favoriteFlight.getDepDelayed());
        assertEquals(ARR_DELAYED, favoriteFlight.getArrDelayed());
        assertEquals(STATUS, favoriteFlight.getStatus());
    }

    @Test
    void favoriteFlightToFavoriteFlightDto() {
        FavoriteFlight favoriteFlight = getFavoriteFlight();

        FavoriteFlightDto favoriteFlightDto = favoriteFlightMapper.favoriteFlightToFavoriteFlightDto(favoriteFlight);

        assertNotNull(favoriteFlight);
        assertEquals(MODEL, favoriteFlightDto.getModel());
        assertEquals(FLIGHT_NUMBER, favoriteFlightDto.getFlightNumber());
        assertEquals(DEP_IATA, favoriteFlightDto.getDepIata());
        assertEquals(DEP_NAME, favoriteFlightDto.getDepName());
        assertEquals(DEP_COUNTRY, favoriteFlightDto.getDepCountry());
        assertEquals(DEP_CITY, favoriteFlightDto.getDepCity());
        assertEquals(ARR_IATA, favoriteFlightDto.getArrIata());
        assertEquals(ARR_NAME, favoriteFlightDto.getArrName());
        assertEquals(ARR_COUNTRY, favoriteFlightDto.getArrCountry());
        assertEquals(ARR_CITY, favoriteFlightDto.getArrCity());
        assertEquals(DEP_TIME, favoriteFlightDto.getDepTime());
        assertEquals(DEP_TIME_UTC, favoriteFlightDto.getDepTimeUtc());
        assertEquals(ARR_TIME, favoriteFlightDto.getArrTime());
        assertEquals(ARR_TIME_UTC, favoriteFlightDto.getArrTimeUtc());
        assertEquals(DEP_ACTUAL, favoriteFlightDto.getDepActual());
        assertEquals(DEP_ACTUAL_UTC, favoriteFlightDto.getDepActualUtc());
        assertEquals(ARR_ACTUAL, favoriteFlightDto.getArrActual());
        assertEquals(ARR_ACTUAL_UTC, favoriteFlightDto.getArrActualUtc());
        assertEquals(DURATION, favoriteFlightDto.getDuration());
        assertEquals(DEP_DELAYED, favoriteFlightDto.getDepDelayed());
        assertEquals(ARR_DELAYED, favoriteFlightDto.getArrDelayed());
        assertEquals(STATUS, favoriteFlightDto.getStatus());
    }

    private static FlightDto getFlightDto() {
        FlightDto flightDto = new FlightDto();
        flightDto.setModel(MODEL);
        flightDto.setFlightNumber(FLIGHT_NUMBER);
        flightDto.setDepIata(DEP_IATA);
        flightDto.setDepName(DEP_NAME);
        flightDto.setDepCountry(DEP_COUNTRY);
        flightDto.setDepCity(DEP_CITY);
        flightDto.setArrIata(ARR_IATA);
        flightDto.setArrName(ARR_NAME);
        flightDto.setArrCountry(ARR_COUNTRY);
        flightDto.setArrCity(ARR_CITY);
        flightDto.setDepTime(DEP_TIME);
        flightDto.setDepTimeUtc(DEP_TIME_UTC);
        flightDto.setArrTime(ARR_TIME);
        flightDto.setArrTimeUtc(ARR_TIME_UTC);
        flightDto.setDepActual(DEP_ACTUAL);
        flightDto.setDepActualUtc(DEP_ACTUAL_UTC);
        flightDto.setArrActual(ARR_ACTUAL);
        flightDto.setArrActualUtc(ARR_ACTUAL_UTC);
        flightDto.setDuration(DURATION);
        flightDto.setDepDelayed(DEP_DELAYED);
        flightDto.setArrDelayed(ARR_DELAYED);
        flightDto.setStatus(STATUS);
        return flightDto;
    }

    private static FavoriteFlight getFavoriteFlight() {
        FavoriteFlight favoriteFlight = new FavoriteFlight();
        favoriteFlight.setModel(MODEL);
        favoriteFlight.setFlightNumber(FLIGHT_NUMBER);
        favoriteFlight.setDepIata(DEP_IATA);
        favoriteFlight.setDepName(DEP_NAME);
        favoriteFlight.setDepCountry(DEP_COUNTRY);
        favoriteFlight.setDepCity(DEP_CITY);
        favoriteFlight.setArrIata(ARR_IATA);
        favoriteFlight.setArrName(ARR_NAME);
        favoriteFlight.setArrCountry(ARR_COUNTRY);
        favoriteFlight.setArrCity(ARR_CITY);
        favoriteFlight.setDepTime(DEP_TIME);
        favoriteFlight.setDepTimeUtc(DEP_TIME_UTC);
        favoriteFlight.setArrTime(ARR_TIME);
        favoriteFlight.setArrTimeUtc(ARR_TIME_UTC);
        favoriteFlight.setDepActual(DEP_ACTUAL);
        favoriteFlight.setDepActualUtc(DEP_ACTUAL_UTC);
        favoriteFlight.setArrActual(ARR_ACTUAL);
        favoriteFlight.setArrActualUtc(ARR_ACTUAL_UTC);
        favoriteFlight.setDuration(DURATION);
        favoriteFlight.setDepDelayed(DEP_DELAYED);
        favoriteFlight.setArrDelayed(ARR_DELAYED);
        favoriteFlight.setStatus(STATUS);
        return favoriteFlight;
    }
}