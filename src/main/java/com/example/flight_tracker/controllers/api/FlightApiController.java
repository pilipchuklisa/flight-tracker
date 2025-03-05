package com.example.flight_tracker.controllers.api;

import com.example.flight_tracker.dto.flight.FlightDto;
import com.example.flight_tracker.services.FlightService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@Validated
@RequiredArgsConstructor
public class FlightApiController {

    private final FlightService flightService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FlightDto> getFlights(
            @RequestParam(name = "flight_number") String flightNumber,
            @RequestParam(name = "dep_iata") @NotBlank(message = "Departure IATA may not be blank") String depIata,
            @RequestParam(name = "arr_iata") @NotBlank(message = "Arrival IATA may not be blank") String arrIata,
            @RequestParam(name = "dep_time")
            @Pattern(regexp = "^$|\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}", message = "Departure time should be valid")
            String depTime) {
        return flightService.searchFlights(flightNumber, depIata, arrIata, depTime);
    }
}
