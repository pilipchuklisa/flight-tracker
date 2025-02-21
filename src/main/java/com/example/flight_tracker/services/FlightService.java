package com.example.flight_tracker.services;

import com.example.flight_tracker.dto.flight.FlightDto;
import com.example.flight_tracker.dto.flight.airlabs.FlightInfoRequest;
import com.example.flight_tracker.dto.flight.airlabs.FlightInfoResponse;
import com.example.flight_tracker.dto.flight.airlabs.FlightScheduleResponse;
import com.example.flight_tracker.services.airlabs.FlightInfoService;
import com.example.flight_tracker.services.airlabs.FlightScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightInfoService flightInfoService;
    private final FlightScheduleService flightScheduleService;

    public List<FlightDto> searchFlights(String flightNumber, String depIata, String arrIata, String depTime) {
         List<FlightScheduleResponse> scheduleResponses =
                 flightScheduleService.getData(flightNumber, depIata, arrIata, depTime);
         List<FlightInfoRequest> infoRequests = getFlightInfoRequests(scheduleResponses);
         List<FlightInfoResponse> infoResponses = flightInfoService.getData(infoRequests);

         return getFlightDto(scheduleResponses, infoResponses);
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

    private List<FlightDto> getFlightDto(List<FlightScheduleResponse> scheduleResponses,
                                         List<FlightInfoResponse> infoResponses) {
        return scheduleResponses
                .stream()
                .map(scheduleResponse -> {
                    FlightDto flightDto = new FlightDto();

                    flightDto.setFlightNumber(scheduleResponse.getFlightNumber());
                    flightDto.setDepIata(scheduleResponse.getDepIata());
                    flightDto.setArrIata(scheduleResponse.getArrIata());
                    flightDto.setDepTime(scheduleResponse.getDepTime());
                    flightDto.setArrTime(scheduleResponse.getArrTime());
                    flightDto.setDepActual(scheduleResponse.getDepActual());
                    flightDto.setArrActual(scheduleResponse.getArrActual());
                    flightDto.setDuration(scheduleResponse.getDuration());
                    flightDto.setStatus(scheduleResponse.getStatus());

                    String model = getModel(scheduleResponse, infoResponses);
                    flightDto.setModel(model);

                    return flightDto;
                })
                .collect(Collectors.toList());
    }

    private String getModel(FlightScheduleResponse scheduleResponse,
                            List<FlightInfoResponse > infoResponses) {
        return infoResponses
                .stream()
                .filter(info -> Objects.equals(info.getFlightIata(), scheduleResponse.getFlightIata()))
                .filter(info -> Objects.equals(info.getFlightIcao(), scheduleResponse.getFlightIcao()))
                .findFirst()
                .orElse(new FlightInfoResponse())
                .getModel();
    }
}
