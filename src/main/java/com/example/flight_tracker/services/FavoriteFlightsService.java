package com.example.flight_tracker.services;

import com.example.flight_tracker.domain.mongo.FavoriteFlight;
import com.example.flight_tracker.dto.flight.FavoriteFlightInfo;
import com.example.flight_tracker.repositories.mongo.FavoriteFlightsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteFlightsService {

    private final FavoriteFlightsRepository favoriteFlightsRepository;

    public List<FavoriteFlightInfo> getAllFlightsByEmail(String email) {
        List<FavoriteFlight> flights = favoriteFlightsRepository.findAllByEmail(email);

        return flights.stream()
                .map(f -> {
                    FavoriteFlightInfo info = new FavoriteFlightInfo();
                    info.setId(f.getId());
                    info.setFlightNumber(f.getFlightNumber());
                    info.setDepIata(f.getDepIata());
                    info.setArrIata(f.getArrIata());
                    info.setDepTime(f.getDepTime());
                    info.setStatus(f.getStatus());
                    info.setDepActual(f.getDepActual());
                    info.setArrActual(f.getArrActual());
                    info.setModel(f.getModel());
                    return info;
                })
                .collect(Collectors.toList());
    }

    public void deleteFlightById(String id) {
        favoriteFlightsRepository.deleteById(id);
    }

    public void saveFlight(FavoriteFlightInfo info, String email) {
        FavoriteFlight flight = new FavoriteFlight();

        flight.setEmail(email);
        flight.setFlightNumber(info.getFlightNumber());
        flight.setDepIata(info.getDepIata());
        flight.setArrIata(info.getArrIata());
        flight.setDepTime(info.getDepTime());
        flight.setStatus(info.getStatus());
        flight.setDepActual(info.getDepActual());
        flight.setArrActual(info.getArrActual());
        flight.setModel(info.getModel());

        favoriteFlightsRepository.save(flight);
    }
}
