package com.example.weatherapp.controller;

import com.example.weatherapp.model.LocationRecord;
import com.example.weatherapp.model.dto.DateDTO;
import com.example.weatherapp.service.impl.LocationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationServiceImpl locationServiceImpl;

    @GetMapping("/best")
    public LocationRecord findBestLocation(@RequestBody DateDTO date) throws IOException, URISyntaxException, InterruptedException {
        return locationServiceImpl.getBestLocation(date.getDate());
    }
}
