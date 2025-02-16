package com.example.flight_tracker.controllers.view;

import com.example.flight_tracker.dto.flight.FavoriteFlightInfo;
import com.example.flight_tracker.services.FavoriteFlightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteFlightsController {

    private final FavoriteFlightsService favoriteFlightsService;

    @GetMapping
    public String showFavoritesPage(Model model) {
        List<FavoriteFlightInfo> flights = favoriteFlightsService.getAllFlightsByEmail("email@gmail.com");
        model.addAttribute("flights", flights);
        return "favorites";
    }
}
