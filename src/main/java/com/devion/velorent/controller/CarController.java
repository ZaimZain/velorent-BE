package com.devion.velorent.controller;

import com.devion.velorent.dto.CarDto;
import com.devion.velorent.dto.DtoMapper;
import com.devion.velorent.entity.Car;
import com.devion.velorent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    // Add new car
    @PostMapping
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto) {
        Car car = DtoMapper.map(carDto, Car.class);
        Car savedCar = carService.addCar(car);
        CarDto savedCarDto = DtoMapper.map(savedCar, CarDto.class);
        return ResponseEntity.ok(savedCarDto);
    }

    // Get all cars
    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        List<CarDto> carDtos = cars.stream()
                .map(car -> DtoMapper.map(car, CarDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(carDtos);
    }
}
