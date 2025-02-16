package com.example.flight_tracker.dto.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FavoriteFlightInfo {

    private String id;
    private String model;

    @JsonProperty(namespace = "flight_number")
    private String flightNumber;

    @JsonProperty(namespace = "dep_iata")
    private String depIata;

    @JsonProperty(namespace = "arr_iata")
    private String arrIata;

    @JsonProperty(namespace = "dep_time")
    private String depTime;

    @JsonProperty(namespace = "arr_time")
    private String arrTime;

    @JsonProperty(namespace = "dep_actual")
    private String depActual;

    @JsonProperty(namespace = "arr_actual")
    private String arrActual;

    private String duration;
    private String status;
}
