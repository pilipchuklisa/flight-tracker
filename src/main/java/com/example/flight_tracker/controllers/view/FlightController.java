package com.example.flight_tracker.controllers.view;

import com.example.flight_tracker.dto.flight.FlightDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/flight-details")
public class FlightController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String showFlightDetailsPage(
            @RequestParam(value = "model", required = false) String model,
            @RequestParam(value = "flight_number", required = false) String flightNumber,
            @RequestParam(value = "dep_iata", required = false) String depIata,
            @RequestParam(value = "dep_name", required = false) String depName,
            @RequestParam(value = "dep_country", required = false) String depCountry,
            @RequestParam(value = "dep_city", required = false) String depCity,
            @RequestParam(value = "arr_iata", required = false) String arrIata,
            @RequestParam(value = "arr_name", required = false) String arrName,
            @RequestParam(value = "arr_country", required = false) String arrCountry,
            @RequestParam(value = "arr_city", required = false) String arrCity,
            @RequestParam(value = "dep_time", required = false) String depTime,
            @RequestParam(value = "dep_time_utc", required = false) String depTimeUtc,
            @RequestParam(value = "arr_time", required = false) String arrTime,
            @RequestParam(value = "arr_time_utc", required = false) String arrTimeUtc,
            @RequestParam(value = "dep_actual", required = false) String depActual,
            @RequestParam(value = "dep_actual_utc", required = false) String depActualUtc,
            @RequestParam(value = "arr_actual", required = false) String arrActual,
            @RequestParam(value = "arr_actual_utc", required = false) String arrActualUtc,
            @RequestParam(value = "duration", required = false) Integer duration,
            @RequestParam(value = "dep_delayed", required = false) Integer depDelayed,
            @RequestParam(value = "arr_delayed", required = false) Integer arrDelayed,
            @RequestParam(value = "status", required = false) String status,
            Model uiModel
    ) throws JsonProcessingException {
        FlightDto flightDto = new FlightDto();
        flightDto.setModel(model);
        flightDto.setFlightNumber(flightNumber);
        flightDto.setDepIata(depIata);
        flightDto.setDepName(depName);
        flightDto.setDepCountry(depCountry);
        flightDto.setDepCity(depCity);
        flightDto.setArrIata(arrIata);
        flightDto.setArrName(arrName);
        flightDto.setArrCountry(arrCountry);
        flightDto.setArrCity(arrCity);
        flightDto.setDepTime(depTime);
        flightDto.setDepTimeUtc(depTimeUtc);
        flightDto.setArrTime(arrTime);
        flightDto.setArrTimeUtc(arrTimeUtc);
        flightDto.setDepActual(depActual);
        flightDto.setDepActualUtc(depActualUtc);
        flightDto.setArrActual(arrActual);
        flightDto.setArrActualUtc(arrActualUtc);
        flightDto.setDuration(duration);
        flightDto.setDepDelayed(depDelayed);
        flightDto.setArrDelayed(arrDelayed);
        flightDto.setStatus(status);

        uiModel.addAttribute("flightDto", flightDto);
        uiModel.addAttribute("flightJson", new ObjectMapper().writeValueAsString(flightDto));

        return "flights/details";
    }
}

