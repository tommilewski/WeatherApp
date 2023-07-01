package com.example.weatherapp.exceptions;

public class NoForecastFoundException extends RuntimeException {
    public NoForecastFoundException(String message) {
        super(message);
    }
}
