package com.example.flight_tracker.domain.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class SearchHistory {

    @Id
    private String id;

    private String email;
    private String flightNumber;
    private String depIata;
    private String arrIata;
    private String depTime;
}
