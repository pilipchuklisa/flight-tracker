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
        userRepository.deleteAll();

        FavoriteFlight favoriteFlight1 = new FavoriteFlight();
        favoriteFlight1.setEmail("email@gmail.com");
        favoriteFlight1.setModel("Boeing 737");
        favoriteFlight1.setFlightNumber("AB123");
        favoriteFlight1.setStatus("В пути");
        favoriteFlight1.setDuration(120);
        favoriteFlight1.setDepCountry("Россия");
        favoriteFlight1.setDepCity("Москва");
        favoriteFlight1.setArrCountry("Германия");
        favoriteFlight1.setArrCity("Берлин");
        favoriteFlight1.setDepName("Шереметьево");
        favoriteFlight1.setDepIata("SVO");
        favoriteFlight1.setDepTimeUtc("2025-02-28T09:00:00Z");
        favoriteFlight1.setDepTime("2025-02-28T12:00");
        favoriteFlight1.setArrName("Тегель");
        favoriteFlight1.setArrIata("TXL");
        favoriteFlight1.setArrTime("2025-02-28T14:00");
        favoriteFlight1.setArrTimeUtc("2025-02-28T11:00:00Z");
        favoriteFlight1.setDepTimeZone("Europe/Moscow");
        favoriteFlight1.setArrTimeZone("Europe/Berlin");

        FavoriteFlight favoriteFlight2 = new FavoriteFlight();
        favoriteFlight2.setEmail("email@gmail.com");
        favoriteFlight2.setModel("Airbus A320");
        favoriteFlight2.setFlightNumber("XY789");
        favoriteFlight2.setStatus("Прибыл");
        favoriteFlight2.setDuration(180);
        favoriteFlight2.setDepCountry("США");
        favoriteFlight2.setDepCity("Нью-Йорк");
        favoriteFlight2.setArrCountry("Франция");
        favoriteFlight2.setArrCity("Париж");
        favoriteFlight2.setDepName("Международный аэропорт имени Джона Кеннеди");
        favoriteFlight2.setDepIata("JFK");
        favoriteFlight2.setDepTimeUtc("2025-02-28T15:30:00Z");
        favoriteFlight2.setDepTime("2025-02-28T10:30");
        favoriteFlight2.setArrName("Шарль-де-Голль");
        favoriteFlight2.setArrIata("CDG");
        favoriteFlight2.setArrTimeUtc("2025-02-28T23:00:00Z");
        favoriteFlight2.setArrTime("2025-02-28T18:00");
        favoriteFlight2.setDepActualUtc("2025-02-28T15:50:00Z");
        favoriteFlight2.setDepActual("2025-02-28T10:50");
        favoriteFlight2.setArrActualUtc("2025-02-28T23:20:00Z");
        favoriteFlight2.setArrActual("2025-02-28T18:20");
        favoriteFlight2.setDepDelayed(20);
        favoriteFlight2.setArrDelayed(20);
        favoriteFlight2.setDepTimeZone("America/New_York");
        favoriteFlight2.setArrTimeZone("Europe/Paris");

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
