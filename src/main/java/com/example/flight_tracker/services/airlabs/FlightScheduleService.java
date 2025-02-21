package com.example.flight_tracker.services.airlabs;

import com.example.flight_tracker.dto.flight.airlabs.FlightResponse;
import com.example.flight_tracker.dto.flight.airlabs.FlightScheduleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class FlightScheduleService {

    public static final String ENDPOINT = "https://airlabs.co/api/v9/schedules";
    public static final String API_KEY_PARAM = "api_key";
    public static final String FLIGHT_NUMBER_PARAM = "flight_number";
    public static final String DEP_IATA_PARAM = "dep_iata";
    public static final String ARR_IATA_PARAM = "arr_iata";
    public static final String DEP_TIME_PARAM = "dep_time_iata";

    private final String apiKey;
    private final RestTemplate restTemplate;

    public FlightScheduleService(@Value("${api-key}") String apiKey, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    public List<FlightScheduleResponse> getData(String flightNumber, String depIata, String arrIata, String depTime) {
        URI uri = UriComponentsBuilder
                .fromUriString(ENDPOINT)
                .queryParam(API_KEY_PARAM, apiKey)
                .queryParam(FLIGHT_NUMBER_PARAM, flightNumber)
                .queryParam(DEP_IATA_PARAM, depIata)
                .queryParam(ARR_IATA_PARAM, arrIata)
                .queryParam(DEP_TIME_PARAM, depTime)
                .build()
                .toUri();

        ResponseEntity<FlightResponse<List<FlightScheduleResponse>>> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        return Objects.requireNonNull(responseEntity.getBody()).getResponse();
    }
}