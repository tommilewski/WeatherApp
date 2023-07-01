package com.example.weatherapp.repository;

import com.example.weatherapp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
