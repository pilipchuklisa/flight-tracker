package com.example.flight_tracker.dto.flight.airlabs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FlightResponse<T> {

    @JsonProperty("response")
    private T response;
}
