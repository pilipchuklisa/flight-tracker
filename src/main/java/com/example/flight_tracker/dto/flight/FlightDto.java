package com.example.flight_tracker.dto.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightDto {

    private String model;

    @JsonProperty("flight_number")
    private String flightNumber;

    @JsonProperty("dep_iata")
    private String depIata;

    @JsonProperty("dep_name")
    private String depName;

    @JsonProperty("dep_country")
    private String depCountry;

    @JsonProperty("dep_city")
    private String depCity;

    @JsonProperty("arr_iata")
    private String arrIata;

    @JsonProperty("arr_name")
    private String arrName;

    @JsonProperty("arr_country")
    private String arrCountry;

    @JsonProperty("arr_city")
    private String arrCity;

    @JsonProperty("dep_time")
    private String depTime;

    @JsonProperty("dep_time_utc")
    private String depTimeUtc;

    @JsonProperty("arr_time")
    private String arrTime;

    @JsonProperty("arr_time_utc")
    private String arrTimeUtc;

    @JsonProperty("dep_actual")
    private String depActual;

    @JsonProperty("dep_actual_utc")
    private String depActualUtc;

    @JsonProperty("arr_actual")
    private String arrActual;

    @JsonProperty("arr_actual_utc")
    private String arrActualUtc;

    private Integer duration;

    @JsonProperty("dep_delayed")
    private Integer depDelayed;

    @JsonProperty("arr_delayed")
    private Integer arrDelayed;

    private String status;
}
