package com.example.flight_tracker.controllers.api;

import com.example.flight_tracker.dto.flight.FlightDto;
import com.example.flight_tracker.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class FlightApiControllerTest {

    @InjectMocks
    private FlightApiController flightApiController;

    @Mock
    private FlightService flightService;

    private MockMvc mockMvc;

    private static final String API_URL = "/api/v1/flights";

    private static final String FLIGHT_NUMBER_PARAM = "flight_number";
    private static final String DEP_IATA_PARAM = "dep_iata";
    private static final String ARR_IATA_PARAM = "arr_iata";
    private static final String DEP_TIME_PARAM = "dep_time";

    private static final String FLIGHT_NUMBER = "AA123";
    private static final String DEP_IATA = "JFK";
    private static final String ARR_IATA = "LAX";
    private static final String DEP_TIME = "2025-02-17T08:00";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(flightApiController).build();
    }

    @Test
    void getFlights() throws Exception {
        FlightDto flightDto = new FlightDto();
        flightDto.setFlightNumber(FLIGHT_NUMBER);
        flightDto.setDepIata(DEP_IATA);
        flightDto.setArrIata(ARR_IATA);
        flightDto.setDepTime(DEP_TIME);

        List<FlightDto> flightDtos = new ArrayList<>();
        flightDtos.add(flightDto);

        Mockito.when(flightService
                        .searchFlights(anyString(), anyString(), anyString(), anyString())).thenReturn(flightDtos);

        mockMvc.perform(MockMvcRequestBuilders.get(API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(FLIGHT_NUMBER_PARAM, FLIGHT_NUMBER)
                        .param(DEP_IATA_PARAM, DEP_IATA)
                        .param(ARR_IATA_PARAM, ARR_IATA)
                        .param(DEP_TIME_PARAM, DEP_TIME))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }
}