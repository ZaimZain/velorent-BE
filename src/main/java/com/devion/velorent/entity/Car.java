package com.devion.velorent.entity;

import com.devion.velorent.enums.CarStatus;
import com.devion.velorent.enums.FuelType;
import com.devion.velorent.enums.Transmission;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    private AppUser renter;

    private String brand;
    private String model;

    @Column(name = "body_type")
    private String bodyType;

    private String color;
    private int mileage;
    private int seat;
    private Integer year;

    @Column(name = "plate_number", length = 20, unique = true)
    private String plateNumber;

    @Column(name = "daily_rate", precision = 10, scale = 2)
    private BigDecimal dailyRate;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_status", nullable = false)
    private CarStatus carStatus;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarImage> carImages;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;
}