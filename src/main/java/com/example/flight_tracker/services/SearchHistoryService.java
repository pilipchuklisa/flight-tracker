package com.example.flight_tracker.services;

import com.example.flight_tracker.domain.mongo.SearchHistory;
import com.example.flight_tracker.dto.flight.FlightSearchRequest;
import com.example.flight_tracker.dto.history.SearchHistoryDto;
import com.example.flight_tracker.mapper.SearchHistoryMapper;
import com.example.flight_tracker.repositories.mongo.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchHistoryService {

    private final SearchHistoryRepository searchHistoryRepository;
    private final SearchHistoryMapper searchHistoryMapper;

    public List<SearchHistoryDto> getAllByEmail(String email) {
        return searchHistoryRepository.findAllByEmail(email)
                .stream()
                .map(searchHistoryMapper::searchHistoryToSearchHistoryDto)
                .collect(Collectors.toList());
    }

    public void deleteById(String id) {
        searchHistoryRepository.deleteById(id);
    }

    public void deleteAll() {
        searchHistoryRepository.deleteAll();
    }

    public SearchHistory saveIfNotExist(FlightSearchRequest request, String email) {
        SearchHistory searchHistory = searchHistoryMapper.flightSearchToSearchHistory(request);
        searchHistory.setEmail(email);

        if (!isExist(searchHistory)) {
            return searchHistoryRepository.save(searchHistory);
        }

        return null;
    }

    private boolean isExist(SearchHistory searchHistory) {
        List<SearchHistory> histories = searchHistoryRepository.findAllByEmail(searchHistory.getEmail());

        if (histories.isEmpty()) {
            return false;
        }

        for (SearchHistory history : histories) {
            if (history.equals(searchHistory)) {
                return true;
            }
        }

        return false;
    }
}
