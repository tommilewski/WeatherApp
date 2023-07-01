package com.example.weatherapp.utils;

import com.example.weatherapp.model.LocationRecord;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ValueCalculator {
    public Map<String, Long> calculateValueFromFormula(Map<String, LocationRecord> locations) {
        Map<String, Long> mapOfValuesFromFormula = new HashMap<>();

        for (Map.Entry<String, LocationRecord> entry : locations.entrySet()) {
            Long surfingScore = (entry.getValue().windSpeed() * 3) + entry.getValue().temperature();
            mapOfValuesFromFormula.put(entry.getKey(), surfingScore);
        }
        return mapOfValuesFromFormula;
    }
}
