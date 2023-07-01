package com.example.weatherapp.utils;

import com.example.weatherapp.model.LocationRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LocationFilterTest {

    private LocationFilter locationFilter;

    @BeforeEach
    void setUp(){
        locationFilter = new LocationFilter();
    }

    @Test
    void filterByRequirements_shouldFilterLocationsBasedOnRequirements() {
        Map<String, LocationRecord> locations = new HashMap<>();
        locations.put("Location1", new LocationRecord("Location1", 12L, 8L, "lat1", "lon1"));
        locations.put("Location2", new LocationRecord("Location2", 20L, 25L, "lat2", "lon2"));
        locations.put("Location3", new LocationRecord("Location3", 10L, 30L, "lat3", "lon3"));

        Map<String, LocationRecord> filteredLocations = locationFilter.filterByRequirements(locations);

        assertEquals(1, filteredLocations.size());
        assertTrue(filteredLocations.containsKey("Location1"));
    }

}