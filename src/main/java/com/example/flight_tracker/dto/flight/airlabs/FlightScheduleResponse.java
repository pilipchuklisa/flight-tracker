package com.example.flight_tracker.dto.flight.airlabs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightScheduleResponse {

    @JsonProperty("flight_iata")
    private String flightIata;

    @JsonProperty("flight_icao")
    private String flightIcao;

    @JsonProperty("flight_number")
    private String flightNumber;

    @JsonProperty("dep_iata")
    private String depIata;

    @JsonProperty("arr_iata")
    private String arrIata;

    @JsonProperty("dep_time")
    private String depTime;

    @JsonProperty("arr_time")
    private String arrTime;

    @JsonProperty("dep_actual")
    private String depActual;

    @JsonProperty("arr_actual")
    private String arrActual;

    @JsonProperty("duration")
    private Integer duration;

    @JsonProperty("status")
    private String status;
}
