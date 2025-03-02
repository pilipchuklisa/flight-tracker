package com.example.flight_tracker.services;

import com.example.flight_tracker.domain.mongo.FavoriteFlight;
import com.example.flight_tracker.dto.flight.FavoriteFlightDto;
import com.example.flight_tracker.dto.flight.FlightDto;
import com.example.flight_tracker.exceptions.ResourceNotFoundException;
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

    public FavoriteFlightDto getFlightById(String id) {
        FavoriteFlight flight = favoriteFlightsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found."));

        return favoriteFlightMapper.favoriteFlightToFavoriteFlightDto(flight);
    }

    public void deleteFlightById(String id) {
        favoriteFlightsRepository.deleteById(id);
    }

    public FavoriteFlight saveIfNotExist(FlightDto dto, String email) {
        FavoriteFlight flight = favoriteFlightMapper.flightDtoToFavoriteFlight(dto);
        flight.setEmail(email);

        if (!isExist(flight)) {
            return favoriteFlightsRepository.save(flight);
        }

        return null;
    }

    private boolean isExist(FavoriteFlight favoriteFlight) {
        List<FavoriteFlight> flights = favoriteFlightsRepository.findAllByEmail(favoriteFlight.getEmail());

        if (flights.isEmpty()) {
            return false;
        }

        for (FavoriteFlight flight : flights) {
            if (flight.equals(favoriteFlight)) {
                return true;
            }
        }

        return false;
    }
}
