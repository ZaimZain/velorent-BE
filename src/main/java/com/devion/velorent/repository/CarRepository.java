package com.devion.velorent.repository;

import com.devion.velorent.entity.Car;
import com.devion.velorent.enums.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByPlateNumber(String plateNumber);

    boolean existsByPlateNumber(String plateNumber);

    boolean existsByPlateNumberAndCarIdNot(String plateNumber, Long carId);

    List<Car> findByCarStatus(CarStatus carStatus);

    List<Car> findByRenterUserId(Long renterId);
}