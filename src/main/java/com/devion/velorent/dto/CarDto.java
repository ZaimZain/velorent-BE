package com.devion.velorent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter
public class CarDto {
    private String brand;
    private String model;
    private String type;
    private int year;
    private double pricePerDay;
}
