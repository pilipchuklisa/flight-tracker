package com.example.flight_tracker.services;

import com.example.flight_tracker.domain.mongo.FavoriteFlight;
import com.example.flight_tracker.dto.flight.FavoriteFlightDto;
import com.example.flight_tracker.dto.flight.FlightDto;
import com.example.flight_tracker.mapper.FavoriteFlightMapper;
import com.example.flight_tracker.repositories.mongo.FavoriteFlightsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteFlightsService {

    private final FavoriteFlightsRepository favoriteFlightsRepository;
    private final FavoriteFlightMapper favoriteFlightMapper;

    public List<FavoriteFlightDto> getAllFlightsByEmail(String email) {
        List<FavoriteFlight> flights = favoriteFlightsRepository.findAllByEmail(email);

        return flights.stream()
                .map(favoriteFlightMapper::favoriteFlightToFavoriteFlightDto)
                .collect(Collectors.toList());
    }

    public void deleteFlightById(String id) {
        favoriteFlightsRepository.deleteById(id);
    }

    public FavoriteFlight saveFlight(FlightDto dto, String email) {
        FavoriteFlight flight = favoriteFlightMapper.flightDtoToFavoriteFlight(dto);
        flight.setEmail(email);

        return favoriteFlightsRepository.save(flight);
    }
}
