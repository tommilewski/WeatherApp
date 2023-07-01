package com.example.weatherapp.utils;

import com.example.weatherapp.model.LocationRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ValueCalculatorTest {

    private ValueCalculator valueCalculator;

    @BeforeEach
    void setUp(){
        valueCalculator = new ValueCalculator();
    }

    @Test
    void calculateValueFromFormula_shouldCalculateSurfingScoreForLocations() {
        Map<String, LocationRecord> locations = new HashMap<>();
        locations.put("Location1", new LocationRecord("Location1", 12L, 8L, "lat1", "lon1"));
        locations.put("Location2", new LocationRecord("Location2", 20L, 25L, "lat2", "lon2"));
        locations.put("Location3", new LocationRecord("Location3", 10L, 30L, "lat3", "lon3"));

        Map<String, Long> valuesFromFormula = valueCalculator.calculateValueFromFormula(locations);

        assertEquals(3, valuesFromFormula.size());
        assertEquals(36L, valuesFromFormula.get("Location1"));
        assertEquals(95L, valuesFromFormula.get("Location2"));
        assertEquals(100L, valuesFromFormula.get("Location3"));
    }

}