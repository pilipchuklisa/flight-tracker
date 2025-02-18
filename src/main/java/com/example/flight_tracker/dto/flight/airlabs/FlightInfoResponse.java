package com.example.flight_tracker.dto.flight.airlabs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightInfoResponse {

    @JsonProperty("flight_iata")
    private String flightIata;

    @JsonProperty("flight_icao")
    private String flightIcao;

    @JsonProperty("model")
    private String model;
}
