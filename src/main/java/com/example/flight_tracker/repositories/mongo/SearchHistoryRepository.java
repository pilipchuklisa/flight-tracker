package com.example.flight_tracker.repositories.mongo;

import com.example.flight_tracker.domain.mongo.SearchHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchHistoryRepository extends MongoRepository<SearchHistory, String> {

    List<SearchHistory> findAllByEmail(String email);
}
