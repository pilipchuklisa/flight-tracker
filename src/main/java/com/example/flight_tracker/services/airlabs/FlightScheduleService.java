package com.example.flight_tracker.services.airlabs;

import com.example.flight_tracker.dto.flight.airlabs.FlightScheduleResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FlightScheduleService {

    private List<FlightScheduleResponse> data = Arrays.asList(
            new FlightScheduleResponse("AA100", "AAL100", "AA100", "JFK", "LAX", "2025-02-17T08:00", "2025-02-17T10:00", "2025-02-17T08:05", "2025-02-17T10:05", 330, "On Time"),
            new FlightScheduleResponse("UA200", "UAL200", "UA200", "ORD", "SFO", "2025-02-18T09:30", "2025-02-18T11:00", "2025-02-18T09:35", "2025-02-18T11:05", 210, "Delayed"),
            new FlightScheduleResponse("DL300", "DAL300", "DL300", "ATL", "LAX", "2025-02-19T10:00", "2025-02-19T12:00", "2025-02-19T10:10", "2025-02-19T12:10", 240, "On Time"),
            new FlightScheduleResponse("AF400", "AFR400", "AF400", "CDG", "JFK", "2025-02-20T14:00", "2025-02-20T16:00", "2025-02-20T14:15", "2025-02-20T16:10", 420, "Cancelled"),
            new FlightScheduleResponse("LH500", "DLH500", "LH500", "FRA", "ORD", "2025-02-21T12:30", "2025-02-21T14:30", "2025-02-21T12:35", "2025-02-21T14:35", 330, "On Time"),
            new FlightScheduleResponse("BA600", "BAW600", "BA600", "FRA", "ORD", "2025-02-22T13:00", "2025-02-22T15:00", "2025-02-22T13:10", "2025-02-22T15:10", 420, "Delayed"),
            new FlightScheduleResponse("SQ700", "SIA700", "SQ700", "SIN", "SFO", "2025-02-23T16:00", "2025-02-23T18:00", "2025-02-23T16:10", "2025-02-23T18:05", 480, "On Time"),
            new FlightScheduleResponse("EK800", "UAE800", "EK800", "DXB", "JFK", "2025-02-24T18:00", "2025-02-24T20:00", "2025-02-24T18:10", "2025-02-24T20:10", 450, "Delayed"),
            new FlightScheduleResponse("QR900", "QTR900", "QR900", "DOH", "LAX", "2025-02-25T20:00", "2025-02-25T22:00", "2025-02-25T20:05", "2025-02-25T22:05", 480, "On Time"),
            new FlightScheduleResponse("VA1000", "VOZ1000", "VA1000", "SYD", "AKL", "2025-02-26T21:00", "2025-02-26T23:00", "2025-02-26T21:05", "2025-02-26T23:05", 180, "Cancelled")
    );

    public List<FlightScheduleResponse> getData(String flightNumber,
                                                String depIata,
                                                String arrIata,
                                                String depTime) {
        return data
                .stream()
                .filter(d -> flightNumber.isBlank() || Objects.equals(d.getFlightNumber(), flightNumber))
                .filter(d -> Objects.equals(d.getDepIata(), depIata))
                .filter(d -> Objects.equals(d.getArrIata(), arrIata))
                .filter(d -> depTime.isBlank() || Objects.equals(d.getDepTime(), depTime))
                .collect(Collectors.toList());
    }
}