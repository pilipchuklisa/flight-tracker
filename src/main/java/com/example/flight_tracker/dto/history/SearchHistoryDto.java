package com.example.flight_tracker.dto.history;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchHistoryDto {

    private String id;

    @JsonProperty("flight_number")
    private String flightNumber;

    @JsonProperty("dep_iata")
    private String depIata;

    @JsonProperty("arr_iata")
    private String arrIata;

    @JsonProperty("dep_time")
    private String depTime;
}
