package com.example.weatherapp.model.dto;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class DateDTO {
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;
}
