package com.example.flight_tracker.repositories.mongo;

import com.example.flight_tracker.domain.mongo.FavoriteFlight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteFlightsRepository extends MongoRepository<FavoriteFlight, String> {

    List<FavoriteFlight> findAllByEmail(String email);
}
