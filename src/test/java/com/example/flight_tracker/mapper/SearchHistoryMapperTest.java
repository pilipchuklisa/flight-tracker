package com.example.flight_tracker.mapper;

import com.example.flight_tracker.domain.mongo.SearchHistory;
import com.example.flight_tracker.dto.flight.FlightSearchRequest;
import com.example.flight_tracker.dto.history.SearchHistoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SearchHistoryMapperTest {

    private SearchHistoryMapper searchHistoryMapper;

    private static final String FLIGHT_NUMBER = "AA123";
    private static final String DEP_IATA = "JFK";
    private static final String ARR_IATA = "LAX";
    private static final String DEP_TIME = "2025-02-17T08:00";

    @BeforeEach
    void setUp() {
        searchHistoryMapper = new SearchHistoryMapperImpl();
    }

    @Test
    void searchHistoryToSearchHistoryDto() {
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setFlightNumber(FLIGHT_NUMBER);
        searchHistory.setDepIata(DEP_IATA);
        searchHistory.setArrIata(ARR_IATA);
        searchHistory.setDepTime(DEP_TIME);

        SearchHistoryDto searchHistoryDto = searchHistoryMapper.searchHistoryToSearchHistoryDto(searchHistory);

        assertNotNull(searchHistory);
        assertEquals(FLIGHT_NUMBER, searchHistoryDto.getFlightNumber());
        assertEquals(DEP_IATA, searchHistoryDto.getDepIata());
        assertEquals(ARR_IATA, searchHistoryDto.getArrIata());
        assertEquals(DEP_TIME, searchHistoryDto.getDepTime());
    }

    @Test
    void flightSearchToSearchHistory() {
        FlightSearchRequest flightSearchRequest = new FlightSearchRequest();
        flightSearchRequest.setFlightNumber(FLIGHT_NUMBER);
        flightSearchRequest.setDepIata(DEP_IATA);
        flightSearchRequest.setArrIata(ARR_IATA);
        flightSearchRequest.setDepTime(DEP_TIME);

        SearchHistory searchHistory = searchHistoryMapper.flightSearchToSearchHistory(flightSearchRequest);

        assertNotNull(searchHistory);
        assertEquals(FLIGHT_NUMBER, searchHistory.getFlightNumber());
        assertEquals(DEP_IATA, searchHistory.getDepIata());
        assertEquals(ARR_IATA, searchHistory.getArrIata());
        assertEquals(DEP_TIME, searchHistory.getDepTime());
    }
}