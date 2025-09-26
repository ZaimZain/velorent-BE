package com.devion.velorent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private String type;
    private int year;
    private double pricePerDay;
    private String transmission;
    private int seats;
    private boolean available;
}