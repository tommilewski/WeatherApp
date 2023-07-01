package com.example.weatherapp.service.impl;

import com.example.weatherapp.client.WeatherApiClient;
import com.example.weatherapp.exceptions.DateOutOfRangeException;
import com.example.weatherapp.exceptions.NoForecastFoundException;
import com.example.weatherapp.model.Location;
import com.example.weatherapp.model.LocationRecord;
import com.example.weatherapp.repository.LocationRepository;
import com.example.weatherapp.service.LocationService;
import com.example.weatherapp.utils.DateChecker;
import com.example.weatherapp.utils.LocationFilter;
import com.example.weatherapp.utils.ValueCalculator;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final DateChecker dateChecker;
    private final LocationFilter locationFilter;
    private final ValueCalculator valueCalculator;
    private final WeatherApiClient weatherApiClient;

    @Override
    public LocationRecord getBestLocation(LocalDate date) throws IOException, URISyntaxException, InterruptedException {

        Map<String, LocationRecord> filteredLocations = locationFilter.filterByRequirements(getLocations(date));

        Map<String, Long> mapOfValuesFromFormula = valueCalculator.calculateValueFromFormula(filteredLocations);

        if (!mapOfValuesFromFormula.isEmpty()) {
            Map.Entry<String, Long> maxEntry = mapOfValuesFromFormula.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .orElseThrow(NoSuchElementException::new);

            Optional<Map.Entry<String, LocationRecord>> mapOfBestLocation = filteredLocations.entrySet()
                    .stream()
                    .filter(o -> o.getValue().locationName().equals(maxEntry.getKey()))
                    .findAny();

            return getBestLocationFromMap(mapOfBestLocation);
        }
        return null;
    }

    private LocationRecord getBestLocationFromMap(Optional<Map.Entry<String, LocationRecord>> mapOfBestLocation) {
        return mapOfBestLocation.map(Map.Entry::getValue).orElse(null);
    }

    private Map<String, LocationRecord> getLocations(LocalDate localDate) throws URISyntaxException, IOException, InterruptedException {
        if (!dateChecker.checkDate(localDate)) {
            throw new DateOutOfRangeException("Date is out of range");
        }

        Map<String, LocationRecord> locations = new HashMap<>();
        List<Location> allLocations = locationRepository.findAll();

        for (Location location : allLocations) {
            List<JsonNode> data = weatherApiClient.getJsonData(location.getUrl());

            JsonNode forecast = data.stream()
                    .filter(node -> node.get("datetime").asText().equals(localDate.toString()))
                    .findAny()
                    .orElseThrow(() -> new NoForecastFoundException("No forecast found"));

            LocationRecord newLocation = new LocationRecord (
                    location.getName(),
                    forecast.get("temp").asLong(),
                    forecast.get("wind_spd").asLong(),
                    location.getLon(),
                    location.getLat());

            locations.put(location.getName(), newLocation);
        }

        return locations;
    }
}
