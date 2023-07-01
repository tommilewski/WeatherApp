package com.example.weatherapp.service;

import com.example.weatherapp.model.LocationRecord;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;

public interface LocationService {

    LocationRecord getBestLocation(LocalDate date) throws IOException, URISyntaxException, InterruptedException;


}
