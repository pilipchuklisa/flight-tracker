package com.example.flight_tracker.services;

import com.example.flight_tracker.domain.mongo.SearchHistory;
import com.example.flight_tracker.dto.flight.FlightSearchRequest;
import com.example.flight_tracker.dto.history.SearchHistoryDto;
import com.example.flight_tracker.repositories.mongo.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchHistoryService {

    private final SearchHistoryRepository searchHistoryRepository;

    public List<SearchHistoryDto> getAllByEmail(String email) {
        return searchHistoryRepository.findAllByEmail(email)
                .stream()
                .map(history -> {
                    SearchHistoryDto dto = new SearchHistoryDto();
                    dto.setFlightNumber(history.getFlightNumber());
                    dto.setDepIata(history.getDepIata());
                    dto.setArrIata(history.getArrIata());
                    dto.setDepTime(history.getDepTime());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public void deleteById(String id) {
        searchHistoryRepository.deleteById(id);
    }

    public void deleteAll() {
        searchHistoryRepository.deleteAll();
    }

    public SearchHistory save(FlightSearchRequest request, String email) {
        SearchHistory searchHistory = new SearchHistory();

        searchHistory.setEmail(email);
        searchHistory.setFlightNumber(request.getFlightNumber());
        searchHistory.setDepIata(request.getDepIata());
        searchHistory.setArrIata(request.getArrIata());
        searchHistory.setDepTime(request.getDepTime());

        return searchHistoryRepository.save(searchHistory);
    }
}
