package com.example.weatherapp.controller;


import com.example.weatherapp.model.LocationRecord;
import com.example.weatherapp.service.impl.LocationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LocationController.class)
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationServiceImpl locationServiceImpl;

    @Test
    public void testFindBestLocation() throws Exception {
        LocationRecord locationRecord = new LocationRecord("City", 20L, 10L, "1.23", "4.56");
        when(locationServiceImpl.getBestLocation(any(LocalDate.class))).thenReturn(locationRecord);

        mockMvc.perform(get("/api/v1/location/best")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"date\": \"2023-06-28\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.locationName").value("City"))
                .andExpect(jsonPath("$.temperature").value(20))
                .andExpect(jsonPath("$.windSpeed").value(10))
                .andExpect(jsonPath("$.lat").value("1.23"))
                .andExpect(jsonPath("$.lon").value("4.56"));
    }
}
