package com.example.flight_tracker.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

    @GetMapping
    public String getSearchPage(@RequestParam(name = "flight_number", required = false) String flightNumber,
                                @RequestParam(name = "dep_iata", required = false) String depIata,
                                @RequestParam(name = "arr_iata", required = false) String arrIata,
                                @RequestParam(name = "dep_time", required = false) String depTime) {
        return "flight/search";
    }
}
