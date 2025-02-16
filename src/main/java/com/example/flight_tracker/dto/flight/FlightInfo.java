package com.example.flight_tracker.dto.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightInfo {

    private String model;

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

    private String duration;
    private String status;
}
