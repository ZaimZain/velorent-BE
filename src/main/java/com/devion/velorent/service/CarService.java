package com.devion.velorent.service;

import com.devion.velorent.dto.car.CarRequestDto;
import com.devion.velorent.dto.car.CarResponseDto;
import com.devion.velorent.entity.AppUser;
import com.devion.velorent.enums.CarStatus;

import java.util.List;

public interface CarService {

    CarResponseDto createCar(CarRequestDto requestDto, AppUser loggedInUser);

    List<CarResponseDto> getAllCars();

    CarResponseDto getCarById(Long carId);

    List<CarResponseDto> getMyCars(AppUser loggedInUser);

    List<CarResponseDto> getCarsByStatus(CarStatus carStatus);

    CarResponseDto updateCar(Long carId, CarRequestDto requestDto, AppUser loggedInUser);

    void deleteCar(Long carId, AppUser loggedInUser);
}