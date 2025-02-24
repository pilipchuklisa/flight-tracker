package com.example.flight_tracker.mapper;

import com.example.flight_tracker.domain.mongo.SearchHistory;
import com.example.flight_tracker.dto.flight.FlightSearchRequest;
import com.example.flight_tracker.dto.history.SearchHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SearchHistoryMapper {

    SearchHistoryDto searchHistoryToSearchHistoryDto(SearchHistory searchHistory);

    SearchHistory flightSearchToSearchHistory(FlightSearchRequest flightSearchRequest);
}
