package com.example.flight_tracker.domain;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Flight {

    @Id
    private String id;
}
