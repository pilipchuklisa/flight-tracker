package com.example.flight_tracker.domain.mongo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(exclude = "id")
@Document
public class FavoriteFlight {

    @Id
    private String id;

    private String email;
    private String model;
    private String flightNumber;
    private String depIata;
    private String depName;
    private String depTimeZone;
    private String depCountry;
    private String depCity;
    private String arrIata;
    private String arrName;
    private String arrTimeZone;
    private String arrCountry;
    private String arrCity;
    private String depTime;
    private String depTimeUtc;
    private String arrTime;
    private String arrTimeUtc;
    private String depActual;
    private String depActualUtc;
    private String arrActual;
    private String arrActualUtc;
    private Integer duration;
    private Integer depDelayed;
    private Integer arrDelayed;
    private String status;
}
