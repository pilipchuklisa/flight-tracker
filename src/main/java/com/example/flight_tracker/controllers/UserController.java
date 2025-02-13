package com.example.flight_tracker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.BASE_URL)
@RequiredArgsConstructor
public class UserController {

    public static final String BASE_URL = "/api/v1/users";
}
