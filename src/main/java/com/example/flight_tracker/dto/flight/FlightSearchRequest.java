package com.example.flight_tracker.dto.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightSearchRequest {

    @JsonProperty(namespace = "flight_number")
    private String flightNumber;

    @JsonProperty(namespace = "dep_iata")
    private String depIata;

    @JsonProperty(namespace = "arr_iata")
    private String arrIata;

    @JsonProperty(namespace = "dep_time")
    private String depTime;
}
