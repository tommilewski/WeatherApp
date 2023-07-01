package com.example.weatherapp.exceptions;

public class DateOutOfRangeException extends RuntimeException {
    public DateOutOfRangeException(String message) {
        super(message);
    }
}
