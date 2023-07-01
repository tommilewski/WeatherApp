package com.example.weatherapp.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateChecker {
    public boolean checkDate(LocalDate date) {
        int ALLOWED_DATE_RANGE = 15;
        return date.isBefore(LocalDate.now().plusDays(ALLOWED_DATE_RANGE));
    }

}
