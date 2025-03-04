package com.example.flight_tracker.domain.mysql;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "airport_time_zones")
public class AirportTimeZone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iata_code", unique = true, nullable = false)
    private String iataCode;

    @Column(name = "timezone", nullable = false)
    private String timeZone;
}
