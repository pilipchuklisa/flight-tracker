package com.example.flight_tracker.dto.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class FlightDto {

    private String model;

    @JsonProperty("flight_number")
    private String flightNumber;

    @NotBlank(message = "flight.dep-iata.not-blank")
    @JsonProperty("dep_iata")
    private String depIata;

    @JsonProperty("dep_name")
    private String depName;

    @JsonProperty("dep_time_zone")
    private String depTimeZone;

    @JsonProperty("dep_country")
    private String depCountry;

    @JsonProperty("dep_city")
    private String depCity;

    @NotBlank(message = "flight.arr-iata.not-blank")
    @JsonProperty("arr_iata")
    private String arrIata;

    @JsonProperty("arr_name")
    private String arrName;

    @JsonProperty("arr_time_zone")
    private String arrTimeZone;

    @JsonProperty("arr_country")
    private String arrCountry;

    @JsonProperty("arr_city")
    private String arrCity;

    @NotBlank(message = "flight.dep-time.not-blank")
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}",
            message = "flight.dep-time.is-valid"
    )
    @JsonProperty("dep_time")
    private String depTime;

    @NotBlank(message = "flight.dep-time-utc.not-blank")
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z",
            message = "flight.dep-time-utc.is-valid"
    )
    @JsonProperty("dep_time_utc")
    private String depTimeUtc;

    @Pattern(
            regexp = "^$|\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}",
            message = "flight.dep-time-actual.is-valid"
    )
    @JsonProperty("dep_actual")
    private String depActual;

    @Pattern(
            regexp = "^$|\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z",
            message = "flight.dep-time-actual-utc.is-valid"
    )
    @JsonProperty("dep_actual_utc")
    private String depActualUtc;

    @Pattern(
            regexp = "^$|\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}",
            message = "flight.arr-time.is-valid"
    )
    @JsonProperty("arr_time")
    private String arrTime;

    @Pattern(
            regexp = "^$|\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z",
            message = "flight.arr-time-utc.is-valid"
    )
    @JsonProperty("arr_time_utc")
    private String arrTimeUtc;

    @Pattern(
            regexp = "^$|\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}",
            message = "flight.arr-time-actual.is-valid"
    )
    @JsonProperty("arr_actual")
    private String arrActual;

    @Pattern(
            regexp = "^$|\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z",
            message = "flight.arr-time-actual-utc.is-valid"
    )
    @JsonProperty("arr_actual_utc")
    private String arrActualUtc;

    private Integer duration;

    @JsonProperty("dep_delayed")
    private Integer depDelayed;

    @JsonProperty("arr_delayed")
    private Integer arrDelayed;

    private String status;
}
