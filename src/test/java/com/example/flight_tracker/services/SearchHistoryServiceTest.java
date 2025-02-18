package com.example.flight_tracker.services;

import com.example.flight_tracker.domain.mongo.SearchHistory;
import com.example.flight_tracker.dto.flight.FlightSearchRequest;
import com.example.flight_tracker.dto.history.SearchHistoryDto;
import com.example.flight_tracker.repositories.mongo.SearchHistoryRepository;
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
class SearchHistoryServiceTest {

    @Mock
    private SearchHistoryRepository searchHistoryRepository;

    @InjectMocks
    private SearchHistoryService searchHistoryService;

    private static final String ID = "idstring";
    private static final String EMAIL = "email@gmail.com";
    private static final String FLIGHT_NUMBER = "AA123";
    private static final String DEP_IATA = "JFK";
    private static final String ARR_IATA = "LAX";
    private static final String DEP_TIME = "2025-02-17T08:00";

    private SearchHistory searchHistory;

    @BeforeEach
    void setUp() {
        searchHistory = new SearchHistory();
        searchHistory.setEmail(EMAIL);
        searchHistory.setFlightNumber(FLIGHT_NUMBER);
        searchHistory.setDepIata(DEP_IATA);
        searchHistory.setArrIata(ARR_IATA);
        searchHistory.setDepTime(DEP_TIME);
    }

    @Test
    void getAllByEmail() {
        List<SearchHistory> searchHistories = new ArrayList<>();
        searchHistories.add(searchHistory);

        Mockito.when(searchHistoryRepository.findAllByEmail(anyString())).thenReturn(searchHistories);

        List<SearchHistoryDto> foundSearchHistories = searchHistoryService.getAllByEmail(EMAIL);

        Assertions.assertEquals(searchHistories.size(), foundSearchHistories.size());
        Mockito.verify(searchHistoryRepository, times(1)).findAllByEmail(anyString());
    }

    @Test
    void deleteAll() {
        searchHistoryService.deleteAll();

        Mockito.verify(searchHistoryRepository, times(1)).deleteAll();
    }

    @Test
    void deleteById() {
        searchHistoryService.deleteById(ID);

        Mockito.verify(searchHistoryRepository, times(1)).deleteById(anyString());
    }

    @Test
    void save() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setFlightNumber(FLIGHT_NUMBER);
        request.setDepIata(DEP_IATA);
        request.setArrIata(ARR_IATA);
        request.setDepTime(DEP_TIME);

        searchHistory.setId(ID);

        Mockito.when(searchHistoryRepository.save(any())).thenReturn(searchHistory);

        SearchHistory savedSearchHistory = searchHistoryService.save(request, EMAIL);

        Assertions.assertSame(searchHistory.getId(), savedSearchHistory.getId());
        Assertions.assertSame(searchHistory.getEmail(), savedSearchHistory.getEmail());
        Mockito.verify(searchHistoryRepository, times(1)).save(any());
    }
}