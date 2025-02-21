package com.example.flight_tracker.controllers.api;

import com.example.flight_tracker.dto.flight.FlightDto;
import com.example.flight_tracker.services.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightApiController {

    private final FlightService flightService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightDto> getFlights(@RequestParam(name = "flight_number") String flightNumber,
                                      @RequestParam(name = "dep_iata") String depIata,
                                      @RequestParam(name = "arr_iata") String arrIata,
                                      @RequestParam(name = "dep_time") String depTime) {
        return flightService.searchFlights(flightNumber, depIata, arrIata, depTime);
    }
}
