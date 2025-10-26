package com.devion.velorent.entity;

import com.devion.velorent.enums.CarStatus;
import com.devion.velorent.enums.FuelType;
import com.devion.velorent.enums.Transmission;
import jakarta.persistence.*;
import lombok.*;
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
    @Column(name = "car_id")
    private Long carId;

    @ManyToOne
    @JoinColumn(name = "renter_id")
    private Renter renter;

    private String brand;
    private String model;

    @Column(name = "body_type")
    private String bodyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    private int seat;
    private int year;

    @Column(name = "plate_number", unique = true)
    private String plateNumber;

    @Column(name = "price_per_day")
    private double pricePerDay;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;
}