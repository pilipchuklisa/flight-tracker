package com.example.flight_tracker.controllers.api;

import com.example.flight_tracker.dto.flight.FlightSearchRequest;
import com.example.flight_tracker.dto.history.SearchHistoryDto;
import com.example.flight_tracker.services.SearchHistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SearchHistoryApiControllerTest {

    @InjectMocks
    private SearchHistoryApiController controller;

    @Mock
    private SearchHistoryService service;

    private MockMvc mockMvc;

    private static final String API_URL = "/api/v1/histories";
    private static final String ID = "idstring";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAllSearchHistory() throws Exception {
        List<SearchHistoryDto> histories = new ArrayList<>();
        histories.add(new SearchHistoryDto());

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("test@example.com");

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(service.getAllByEmail(anyString())).thenReturn(histories);

        mockMvc.perform(get(API_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(histories.size())));
    }

    @Test
    void deleteSearchHistoryById() throws Exception {
        mockMvc.perform(delete(API_URL + "/" + ID))
                .andExpect(status().isNoContent());
        Mockito.verify(service, times(1)).deleteById(anyString());
    }

    @Test
    void deleteAllSearchHistory() throws Exception {
        mockMvc.perform(delete(API_URL))
                .andExpect(status().isNoContent());
        Mockito.verify(service, times(1)).deleteAll();
    }

    @Test
    void createSearchHistory() throws Exception {
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("test@example.com");

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        String jsonObject = new ObjectMapper().writeValueAsString(new FlightSearchRequest());

        mockMvc.perform(post(API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                .andExpect(status().isNoContent());
    }
}