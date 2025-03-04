package com.example.flight_tracker.repositories.mysql;

import com.example.flight_tracker.domain.mysql.AirportTimeZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportTimeZoneRepository extends JpaRepository<AirportTimeZone, Long> {

    Optional<AirportTimeZone> findByIataCode(String iataCode);
}
