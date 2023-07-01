package com.example.weatherapp.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateCheckerTest {

    private DateChecker dateChecker;

    @BeforeEach
    void setUp(){
        dateChecker = new DateChecker();
    }

    @Test
    void checkDate_shouldReturnTrue_whenDateIsWithinAllowedRange() {
        LocalDate currentDate = LocalDate.now();

        LocalDate dateWithinRange = currentDate.plusDays(10);
        boolean result = dateChecker.checkDate(dateWithinRange);

        assertTrue(result);
    }

    @Test
    void checkDate_shouldReturnFalse_whenDateIsOutsideAllowedRange() {
        LocalDate currentDate = LocalDate.now();

        LocalDate dateOutsideRange = currentDate.plusDays(20);
        boolean result = dateChecker.checkDate(dateOutsideRange);

        assertFalse(result);
    }

}