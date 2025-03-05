package com.example.flight_tracker.dto.flight;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSearchRequest {

    @JsonProperty("flight_number")
    private String flightNumber;

    @NotBlank(message = "flight.dep-iata.not-blank")
    @JsonProperty("dep_iata")
    private String depIata;

    @NotBlank(message = "flight.arr-iata.not-blank")
    @JsonProperty("arr_iata")
    private String arrIata;

    @Pattern(regexp = "^$|\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}", message = "flight.dep-time.is-valid")
    @JsonProperty("dep_time")
    private String depTime;
}
