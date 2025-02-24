package com.example.flight_tracker.mapper;

import com.example.flight_tracker.domain.mongo.FavoriteFlight;
import com.example.flight_tracker.dto.flight.FavoriteFlightDto;
import com.example.flight_tracker.dto.flight.FlightDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FavoriteFlightMapper {

    FavoriteFlight flightDtoToFavoriteFlight(FlightDto flightDto);

    FavoriteFlightDto favoriteFlightToFavoriteFlightDto(FavoriteFlight favoriteFlight);
}
