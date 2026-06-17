package com.devion.velorent.dto.car;

import com.devion.velorent.enums.CarStatus;
import com.devion.velorent.enums.FuelType;
import com.devion.velorent.enums.Transmission;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarResponseDto {

    private Long carId;

    private Long agentId;
    private String agentUsername;
    private String agentFullName;

    private String brand;
    private String model;
    private String bodyType;
    private String color;
    private Integer mileage;
    private Integer seat;
    private Integer year;
    private String plateNumber;
    private BigDecimal dailyRate;
    private String description;
    private FuelType fuelType;
    private Transmission transmission;
    private CarStatus carStatus;

    private List<String> imageUrls;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}