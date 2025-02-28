package com.example.flight_tracker.services.airlabs;

import com.example.flight_tracker.dto.flight.airlabs.FlightInfoRequest;
import com.example.flight_tracker.dto.flight.airlabs.FlightInfoResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FlightInfoService {

    private List<FlightInfoResponse> data = Arrays.asList(
            new FlightInfoResponse("AA100", "AAL100", "Boeing 737"),
            new FlightInfoResponse("UA200", "UAL200", "Airbus A320"),
            new FlightInfoResponse("DL300", "DAL300", "Boeing 777"),
            new FlightInfoResponse("AF400", "AFR400", "Airbus A350"),
            new FlightInfoResponse("LH500", "DLH500", "Boeing 787"),
            new FlightInfoResponse("BA600", "BAW600", "Airbus A380"),
            new FlightInfoResponse("SQ700", "SIA700", "Boeing 787 Dreamliner"),
            new FlightInfoResponse("EK800", "UAE800", "Airbus A330"),
            new FlightInfoResponse("QR900", "QTR900", "Boeing 767"),
            new FlightInfoResponse("VA1000", "VOZ1000", "Embraer 190")
        );

    public List<FlightInfoResponse> getData(List<FlightInfoRequest> requests) {
        return requests
                .stream()
                .map(request -> getData(request.getFlightIcao(), request.getFlightIata()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public FlightInfoResponse getData(String flightIcao, String flightIata) {
        return data
                .stream()
                .filter(d -> Objects.equals(d.getFlightIata(), flightIata))
                .filter(d -> Objects.equals(d.getFlightIcao(), flightIcao))
                .findAny()
                .orElse(null);
    }
}
