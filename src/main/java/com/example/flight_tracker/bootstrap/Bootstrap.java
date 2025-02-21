package com.example.flight_tracker.bootstrap;

import com.example.flight_tracker.domain.mongo.FavoriteFlight;
import com.example.flight_tracker.domain.mongo.SearchHistory;
import com.example.flight_tracker.domain.mysql.Role;
import com.example.flight_tracker.domain.mysql.User;
import com.example.flight_tracker.repositories.mongo.FavoriteFlightsRepository;
import com.example.flight_tracker.repositories.mongo.SearchHistoryRepository;
import com.example.flight_tracker.repositories.mysql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final FavoriteFlightsRepository favoriteFlightsRepository;
    private final UserRepository userRepository;
    private final SearchHistoryRepository searchHistoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        favoriteFlightsRepository.deleteAll();
        searchHistoryRepository.deleteAll();

        FavoriteFlight favoriteFlight1 = new FavoriteFlight();
        favoriteFlight1.setModel("Boeing 737");
        favoriteFlight1.setDepTime("2025-02-17T08:00");
        favoriteFlight1.setStatus("On Time");
        favoriteFlight1.setEmail("email@gmail.com");
        favoriteFlight1.setFlightNumber("AA123");
        favoriteFlight1.setDepIata("JFK");
        favoriteFlight1.setArrIata("LAX");
        favoriteFlight1.setDepActual("2025-02-17T08:10");
        favoriteFlight1.setArrActual("2025-02-17T11:00");

        FavoriteFlight favoriteFlight2 = new FavoriteFlight();
        favoriteFlight2.setModel("Airbus A320");
        favoriteFlight2.setDepTime("2025-02-18T14:30");
        favoriteFlight2.setStatus("Delayed");
        favoriteFlight2.setEmail("email@gmail.com");
        favoriteFlight2.setFlightNumber("BA456");
        favoriteFlight2.setDepIata("LHR");
        favoriteFlight2.setArrIata("CDG");
        favoriteFlight2.setDepActual("2025-02-18T15:00");
        favoriteFlight2.setArrActual("2025-02-18T17:30");

        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setEmail("email@gmail.com");
        searchHistory.setFlightNumber("BA456");
        searchHistory.setDepIata("LHR");
        searchHistory.setArrIata("CDG");
        searchHistory.setDepTime("2025-02-18T14:30");

        SearchHistory searchHistory2 = new SearchHistory();
        searchHistory2.setEmail("email@gmail.com");
        searchHistory2.setFlightNumber("");
        searchHistory2.setDepIata("JFK");
        searchHistory2.setArrIata("LAX");
        searchHistory2.setDepTime("");

        User user = new User();
        user.setName("User");
        user.setEmail("email@gmail.com");
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode("12345678"));
        user.setEnable(true);

        userRepository.save(user);
        favoriteFlightsRepository.save(favoriteFlight1);
        favoriteFlightsRepository.save(favoriteFlight2);
        searchHistoryRepository.save(searchHistory);
        searchHistoryRepository.save(searchHistory2);
    }
}
