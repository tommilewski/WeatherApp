package com.example.weatherapp.model;

public record LocationRecord(String locationName, Long temperature, Long windSpeed, String lat, String lon) {
}
