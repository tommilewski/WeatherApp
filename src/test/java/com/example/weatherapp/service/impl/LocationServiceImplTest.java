package com.example.weatherapp.service.impl;

import com.example.weatherapp.client.WeatherApiClient;
import com.example.weatherapp.exceptions.DateOutOfRangeException;
import com.example.weatherapp.model.Location;
import com.example.weatherapp.model.LocationRecord;
import com.example.weatherapp.repository.LocationRepository;
import com.example.weatherapp.utils.DateChecker;
import com.example.weatherapp.utils.LocationFilter;
import com.example.weatherapp.utils.ValueCalculator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private DateChecker dateChecker;
    @Mock
    private LocationFilter locationFilter;
    @Mock
    private ValueCalculator valueCalculator;

    @Mock
    private WeatherApiClient weatherApiClient;

    @InjectMocks
    private LocationServiceImpl locationServiceImpl;


    @Test
    void getBestLocation_shouldReturnLocationRecord_whenValidDateAndDataAvailable() throws IOException, URISyntaxException, InterruptedException {
        LocalDate date = LocalDate.now();

        Map<String, LocationRecord> filteredLocations = new HashMap<>();
        filteredLocations.put("Location1", new LocationRecord("Location1", 10L, 15L, "lat1", "lon1"));
        filteredLocations.put("Location2", new LocationRecord("Location2", 20L, 25L, "lat2", "lon2"));
        Map<String, Long> mapOfValuesFromFormula = new HashMap<>();
        mapOfValuesFromFormula.put("Location1", 30L);
        mapOfValuesFromFormula.put("Location2", 40L);

        Mockito.when(locationFilter.filterByRequirements(Mockito.any())).thenReturn(filteredLocations);
        Mockito.when(valueCalculator.calculateValueFromFormula(Mockito.any())).thenReturn(mapOfValuesFromFormula);
        Mockito.when(dateChecker.checkDate(Mockito.any())).thenReturn(true);
        Mockito.when(weatherApiClient.getJsonData(Mockito.anyString())).thenReturn(getMockJsonData());
        Mockito.when(locationRepository.findAll()).thenReturn(getMockLocations());

        LocationRecord result = locationServiceImpl.getBestLocation(date);

        assertEquals(new LocationRecord("Location2", 20L, 25L, "lat2", "lon2"), result);
    }

    @Test
    void getBestLocation_shouldReturnNull_whenValidDateAndNoDataAvailable() throws IOException, URISyntaxException, InterruptedException {
        LocalDate date = LocalDate.now();
        Map<String, LocationRecord> filteredLocations = new HashMap<>();
        Map<String, Long> mapOfValuesFromFormula = new HashMap<>();

        Mockito.when(locationFilter.filterByRequirements(Mockito.any())).thenReturn(filteredLocations);
        Mockito.when(valueCalculator.calculateValueFromFormula(Mockito.any())).thenReturn(mapOfValuesFromFormula);
        Mockito.when(dateChecker.checkDate(Mockito.any())).thenReturn(true);
        Mockito.when(weatherApiClient.getJsonData(Mockito.anyString())).thenReturn(getMockJsonData());
        Mockito.when(locationRepository.findAll()).thenReturn(getMockLocations());

        LocationRecord result = locationServiceImpl.getBestLocation(date);

        assertNull(result);
    }

    @Test
    void getBestLocation_shouldThrowDateOutOfRangeException_whenDateOutOfRange() {
        LocalDate date = LocalDate.now();

        Mockito.when(dateChecker.checkDate(Mockito.any())).thenReturn(false);

        assertThrows(DateOutOfRangeException.class, () -> locationServiceImpl.getBestLocation(date));
    }

    private List<JsonNode> getMockJsonData() {
        List<JsonNode> jsonData = new ArrayList<>();

        JsonNode node1 = createJsonNode("2023-06-28", 10L, 15L);
        JsonNode node2 = createJsonNode("2023-06-28", 20L, 25L);

        jsonData.add(node1);
        jsonData.add(node2);
        return jsonData;
    }

    private JsonNode createJsonNode(String datetime, Long temp, Long wind_spd) {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode node = objectMapper.createObjectNode();

        node.put("datetime", datetime);
        node.put("temp", temp);
        node.put("wind_spd", wind_spd);
        return node;
    }

    private List<Location> getMockLocations() {
        List<Location> locations = new ArrayList<>();

        Location location1 = new Location();
        location1.setName("Location1");
        location1.setUrl("url1");
        location1.setLat("lat1");
        location1.setLon("lon1");

        Location location2 = new Location();
        location2.setName("Location2");
        location2.setUrl("url2");
        location2.setLat("lat2");
        location2.setLon("lon2");

        locations.add(location1);
        locations.add(location2);
        return locations;
    }
}
