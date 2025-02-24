package com.example.flight_tracker.mapper;

import com.example.flight_tracker.dto.flight.airlabs.FlightInfoRequest;
import com.example.flight_tracker.dto.flight.airlabs.FlightScheduleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FlightMapperTest {

    private FlightMapper flightMapper;

    private static final String FLIGHT_IATA = "UA410";
    private static final String FLIGHT_ICAO = "UAL410";

    @BeforeEach
    void setUp() {
        flightMapper = new FlightMapperImpl();
    }

    @Test
    void flightScheduleResponseToFlightInfoRequest() {
        FlightScheduleResponse flightScheduleResponse = new FlightScheduleResponse();
        flightScheduleResponse.setFlightIata(FLIGHT_IATA);
        flightScheduleResponse.setFlightIcao(FLIGHT_ICAO);

        FlightInfoRequest flightInfoRequest = flightMapper
                .flightScheduleResponseToFlightInfoRequest(flightScheduleResponse);

        assertNotNull(flightInfoRequest);
        assertEquals(FLIGHT_IATA, flightInfoRequest.getFlightIata());
        assertEquals(FLIGHT_ICAO, flightInfoRequest.getFlightIcao());
    }
}