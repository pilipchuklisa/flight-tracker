package com.example.flight_tracker.mapper;

import com.example.flight_tracker.dto.flight.airlabs.FlightInfoRequest;
import com.example.flight_tracker.dto.flight.airlabs.FlightScheduleResponse;
import org.mapstruct.Mapper;

@Mapper
public interface FlightMapper {

    FlightInfoRequest flightScheduleResponseToFlightInfoRequest(FlightScheduleResponse flightScheduleResponse);
}
