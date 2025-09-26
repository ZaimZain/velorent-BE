package com.devion.velorent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String type;
    private int year;

    @Column(name = "price_per_day")
    private double pricePerDay;

    private boolean available = true;
    private String transmission = "Auto";
    private int seats = 5;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;
}