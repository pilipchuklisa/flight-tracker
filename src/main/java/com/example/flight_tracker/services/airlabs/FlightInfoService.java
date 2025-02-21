package com.example.flight_tracker.services.airlabs;

import com.example.flight_tracker.dto.flight.airlabs.FlightInfoRequest;
import com.example.flight_tracker.dto.flight.airlabs.FlightInfoResponse;
import com.example.flight_tracker.dto.flight.airlabs.FlightResponse;
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
import java.util.stream.Collectors;

@Service
public class FlightInfoService {

    public static final String ENDPOINT = "https://airlabs.co/api/v9/flight";
    public static final String API_KEY_PARAM = "api_key";
    public static final String FLIGHT_ICAO_PARAM = "flight_icao";
    public static final String FLIGHT_IATA_PARAM = "flight_iata";

    private final String apiKey;
    private final RestTemplate restTemplate;

    public FlightInfoService(@Value("${api-key}") String apiKey, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    public List<FlightInfoResponse> getData(List<FlightInfoRequest> requests) {
        return requests
                .stream()
                .map(request -> getData(request.getFlightIcao(), request.getFlightIata()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public FlightInfoResponse getData(String flightIcao, String flightIata) {
        URI uri = UriComponentsBuilder
                .fromUriString(ENDPOINT)
                .queryParam(API_KEY_PARAM, apiKey)
                .queryParam(FLIGHT_ICAO_PARAM, flightIcao)
                .queryParam(FLIGHT_IATA_PARAM, flightIata)
                .build()
                .toUri();

        ResponseEntity<FlightResponse<FlightInfoResponse>> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        return Objects.requireNonNull(responseEntity.getBody()).getResponse();
    }
}
