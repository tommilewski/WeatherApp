package com.example.weatherapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "location_name")
    private String name;

    private String url;

    private String lon;
    private String lat;
}
