package com.example.flight_tracker.services;

import com.example.flight_tracker.dto.flight.FlightInfo;
import com.example.flight_tracker.dto.flight.airlabs.FlightInfoRequest;
import com.example.flight_tracker.dto.flight.airlabs.FlightInfoResponse;
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
import java.util.stream.Collectors;

@Service
public class FlightService {

    public static final String ENDPOINT = "https://airlabs.co/api/v9";
    public static final String API_KEY_PARAM = "api_key";

    private final String apiKey;
    private final RestTemplate restTemplate;

    public FlightService(@Value("${api-key}") String apiKey, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    private static class FlightScheduleParams {

        public static final String DEP_IATA_PARAM = "dep_iata";
        public static final String ARR_IATA_PARAM = "arr_iata";
    }

    private static class FlightInfoParams {

        public static final String FLIGHT_ICAO_PARAM = "dep_iata";
        public static final String FLIGHT_IATA_PARAM = "arr_iata";
    }

    //TODO: заменить request to flight info to airline fleet

    public List<FlightInfo> searchFlights(String flightNumber, String depIata, String arrIata, String depTime) {
         List<FlightScheduleResponse> scheduleResponses =
                 getDataFromFlightSchedule(flightNumber, depIata, arrIata, depTime);
         List<FlightInfoRequest> infoRequests = getFlightInfoRequests(scheduleResponses);
         List<FlightInfoResponse> infoResponses = getDataFromFlightInfo(infoRequests);

         return getFlightInfo(scheduleResponses, infoResponses);
    }

    private List<FlightScheduleResponse> getDataFromFlightSchedule(String flightNumber, String depIata,
                                                                             String arrIata, String depTime) {
        URI uri = UriComponentsBuilder
                .fromUriString(ENDPOINT + "/schedules")
                .queryParam(API_KEY_PARAM, apiKey)
                .queryParam(FlightScheduleParams.DEP_IATA_PARAM, depIata)
                .queryParam(FlightScheduleParams.ARR_IATA_PARAM, arrIata)
                .build()
                .toUri();

        ResponseEntity<FlightResponse<List<FlightScheduleResponse>>> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        return Objects.requireNonNull(responseEntity.getBody()).getResponse()
                .stream()
                .filter(r -> flightNumber.isBlank() || Objects.equals(r.getFlightNumber(), flightNumber))
                .collect(Collectors.toList());
    }

    private List<FlightInfoRequest> getFlightInfoRequests(List<FlightScheduleResponse> responses) {
        return responses
                .stream()
                .map(response -> {
                    FlightInfoRequest request = new FlightInfoRequest();
                    request.setFlightIcao(response.getFlightIcao());
                    request.setFlightIata(response.getFlightIata());
                    return request;
                })
                .distinct()
                .collect(Collectors.toList());
    }

    private List<FlightInfoResponse> getDataFromFlightInfo(List<FlightInfoRequest> requests) {
        return requests
                .stream()
                .map(request -> getDataFromFlightInfo(request.getFlightIcao(), request.getFlightIata()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private FlightInfoResponse getDataFromFlightInfo(String flightIcao, String flightIata) {
        URI uri = UriComponentsBuilder
                .fromUriString(ENDPOINT + "/flight")
                .queryParam(API_KEY_PARAM, apiKey)
                .queryParam(FlightInfoParams.FLIGHT_ICAO_PARAM, flightIcao)
                .queryParam(FlightInfoParams.FLIGHT_IATA_PARAM, flightIata)
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

    private List<FlightInfo> getFlightInfo(List<FlightScheduleResponse> scheduleResponses,
                                           List<FlightInfoResponse> infoResponses) {
        return scheduleResponses
                .stream()
                .map(scheduleResponse -> {
                    FlightInfo flightInfo = new FlightInfo();

                    flightInfo.setFlightNumber(scheduleResponse.getFlightNumber());
                    flightInfo.setDepIata(scheduleResponse.getDepIata());
                    flightInfo.setArrIata(scheduleResponse.getArrIata());
                    flightInfo.setDepTime(scheduleResponse.getDepTime());
                    flightInfo.setArrTime(scheduleResponse.getArrTime());
                    flightInfo.setDepActual(scheduleResponse.getDepActual());
                    flightInfo.setArrActual(scheduleResponse.getArrActual());
                    flightInfo.setDuration(scheduleResponse.getDuration());
                    flightInfo.setStatus(scheduleResponse.getStatus());

                    String model = getModel(scheduleResponse, infoResponses);
                    flightInfo.setModel(model);

                    return flightInfo;
                })
                .collect(Collectors.toList());
    }

    private String getModel(FlightScheduleResponse scheduleResponse,
                            List<FlightInfoResponse> infoResponses) {
        return infoResponses
                .stream()
                .filter(info -> Objects.equals(info.getFlightIata(), scheduleResponse.getFlightIata()))
                .filter(info -> Objects.equals(info.getFlightIcao(), scheduleResponse.getFlightIcao()))
                .findFirst()
                .orElse(new FlightInfoResponse())
                .getModel();
    }
}
