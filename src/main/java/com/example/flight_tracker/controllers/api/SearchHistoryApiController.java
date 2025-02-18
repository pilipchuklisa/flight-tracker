package com.example.flight_tracker.controllers.api;

import com.example.flight_tracker.dto.flight.FlightSearchRequest;
import com.example.flight_tracker.dto.history.SearchHistoryDto;
import com.example.flight_tracker.services.SearchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/histories")
@RequiredArgsConstructor
public class SearchHistoryApiController {

    private final SearchHistoryService searchHistoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SearchHistoryDto> getAllSearchHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return searchHistoryService.getAllByEmail(email);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSearchHistoryById(@PathVariable String id) {
        searchHistoryService.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllSearchHistory() {
        searchHistoryService.deleteAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createSearchHistory(@RequestBody FlightSearchRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        searchHistoryService.save(request, email);
    }
}
