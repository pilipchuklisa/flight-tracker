package com.example.flight_tracker.controllers.view;

import com.example.flight_tracker.dto.history.SearchHistoryDto;
import com.example.flight_tracker.dto.user.UserDto;
import com.example.flight_tracker.services.SearchHistoryService;
import com.example.flight_tracker.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;
    private final SearchHistoryService searchHistoryService;

    @GetMapping
    public String showProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        UserDto user = userService.findUserByEmail(email);
        List<SearchHistoryDto> histories = searchHistoryService.getAllByEmail(email);

        model.addAttribute("user", user);
        model.addAttribute("histories", histories);

        return "account/profile";
    }

    @GetMapping("/sign-up")
    public String showRegisterForm() {
        return "account/sign-up";
    }

    @GetMapping("/sign-in")
    public String showLoginForm() {
        return "account/sign-in";
    }

    @GetMapping("/verify")
    public String showVerifyForm(@RequestParam String email) {
        return "account/verify";
    }
}
