package com.example.flight_tracker.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {

    private String error;
    private String message;
    private List<Detail> details;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Detail {

        private String field;
        private String description;
    }
}
