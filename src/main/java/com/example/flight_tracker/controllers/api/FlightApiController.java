package com.example.flight_tracker.controllers.api;

import com.example.flight_tracker.dto.flight.FlightInfo;
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
    public List<FlightInfo> getFlights(@RequestParam(name = "flight_number") String flightNumber,
                                       @RequestParam(name = "dep_iata") String depIata,
                                       @RequestParam(name = "arr_iata") String arrIata,
                                       @RequestParam(name = "dep_time") String depTime) {

        List<FlightInfo> flightInfos = flightService.searchFlights(flightNumber, depIata, arrIata, depTime);

        System.out.println("flightInfos");
        flightInfos.forEach(System.out::println);

        return flightInfos;
    }
}
