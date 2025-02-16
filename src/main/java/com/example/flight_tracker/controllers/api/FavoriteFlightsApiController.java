package com.example.flight_tracker.controllers.api;

import com.example.flight_tracker.dto.flight.FavoriteFlightInfo;
import com.example.flight_tracker.services.FavoriteFlightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return favoriteFlightsService.getAllFlightsByEmail("email@gmail.com");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlightById(@PathVariable String id) {
       favoriteFlightsService.deleteFlightById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createFlight(@RequestBody FavoriteFlightInfo info) {
        favoriteFlightsService.saveFlight(info, "email@gmail.com");
    }
}
