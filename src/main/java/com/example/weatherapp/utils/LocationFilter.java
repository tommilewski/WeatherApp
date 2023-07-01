package com.example.weatherapp.utils;

import com.example.weatherapp.model.LocationRecord;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class LocationFilter {
    private final int MIN_WIND_SPEED = 5;
    private final int MAX_WIND_SPEED = 18;
    private final int MIN_TEMPERATURE = 5;
    private final int MAX_TEMPERATURE = 35;

    public Map<String, LocationRecord> filterByRequirements(Map<String, LocationRecord> locations) {
        return locations.entrySet().stream()
                .filter(o -> checkIfValueInRange(o.getValue().windSpeed(), MIN_WIND_SPEED, MAX_WIND_SPEED))
                .filter(o -> checkIfValueInRange(o.getValue().temperature(), MIN_TEMPERATURE, MAX_TEMPERATURE))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    private boolean checkIfValueInRange(Long value, int min, int max) {
        return value >= min && value <= max;
    }
}
