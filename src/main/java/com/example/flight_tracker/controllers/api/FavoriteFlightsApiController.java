package com.example.flight_tracker.controllers.api;

import com.example.flight_tracker.dto.flight.FavoriteFlightInfo;
import com.example.flight_tracker.dto.flight.FlightInfo;
import com.example.flight_tracker.services.FavoriteFlightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
@RequiredArgsConstructor
public class FavoriteFlightsApiController {

    private final FavoriteFlightsService favoriteFlightsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FavoriteFlightInfo> getAllFlights() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return favoriteFlightsService.getAllFlightsByEmail(email);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlightById(@PathVariable String id) {
       favoriteFlightsService.deleteFlightById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createFlight(@RequestBody FlightInfo info) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        favoriteFlightsService.saveFlight(info, email);
    }
}
