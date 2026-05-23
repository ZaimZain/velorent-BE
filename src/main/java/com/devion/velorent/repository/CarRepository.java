package com.devion.velorent.repository;

import com.devion.velorent.entity.Car;
import com.devion.velorent.enums.CarStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByPlateNumber(String plateNumber);

    boolean existsByPlateNumber(String plateNumber);

    boolean existsByPlateNumberAndCarIdNot(String plateNumber, Long carId);

    @EntityGraph(attributePaths = {"carImages", "renter"})
    Optional<Car> findByCarId(Long carId);

    @EntityGraph(attributePaths = {"carImages", "renter"})
    List<Car> findByRenterUserId(Long renterId);

    @EntityGraph(attributePaths = {"carImages", "renter"})
    List<Car> findByRenterUserIdAndCarStatus(Long renterId, CarStatus carStatus);
}