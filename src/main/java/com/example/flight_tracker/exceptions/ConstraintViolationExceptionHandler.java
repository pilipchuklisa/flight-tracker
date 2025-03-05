package com.example.flight_tracker.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ConstraintViolationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetail> handleConstraintViolationException(ConstraintViolationException ex) {

        List<ErrorDetail.Detail> details = Arrays.stream(ex.getMessage().split(","))
                .map(er -> {
                    String[] data = er.split(":");
                    String field = data[0].split("\\.")[1].trim();
                    String description = data[1].trim();
                    return new ErrorDetail.Detail(field, description);
                })
                .toList();

        return new ResponseEntity<>(
                new ErrorDetail(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(), details),
                HttpStatus.BAD_REQUEST);
    }
}
