package com.devion.velorent.service.impl;

import com.devion.velorent.dto.car.CarRequestDto;
import com.devion.velorent.dto.car.CarResponseDto;
import com.devion.velorent.entity.AppUser;
import com.devion.velorent.entity.Car;
import com.devion.velorent.entity.CarImage;
import com.devion.velorent.enums.CarStatus;
import com.devion.velorent.exception.BadRequestException;
import com.devion.velorent.exception.ResourceNotFoundException;
import com.devion.velorent.repository.CarRepository;
import com.devion.velorent.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    @Transactional
    public CarResponseDto createCar(CarRequestDto requestDto, AppUser loggedInUser) {
        validateLoggedInUser(loggedInUser);
        validateCarRequest(requestDto);

        if (carRepository.existsByPlateNumber(requestDto.getPlateNumber())) {
            throw new BadRequestException("Plate number already exists: " + requestDto.getPlateNumber());
        }

        Car car = new Car();
        car.setRenter(loggedInUser);

        mapRequestToEntity(car, requestDto);
        setCarImages(car, requestDto.getImageUrls());

        Car savedCar = carRepository.save(car);

        return mapToResponseDto(savedCar);
    }

    /**
     * Important:
     * This does NOT return all cars in the system.
     * It only returns cars owned by logged-in renter.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CarResponseDto> getAllCars(AppUser loggedInUser) {
        validateLoggedInUser(loggedInUser);

        return carRepository.findByRenterUserId(loggedInUser.getUserId())
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Important:
     * Even if user passes another renter's carId,
     * backend will block it using validateCarOwner().
     */
    @Override
    @Transactional(readOnly = true)
    public CarResponseDto getCarById(Long carId, AppUser loggedInUser) {
        validateLoggedInUser(loggedInUser);

        Car car = findCarEntityById(carId);

        validateCarOwner(car, loggedInUser);

        return mapToResponseDto(car);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarResponseDto> getMyCars(AppUser loggedInUser) {
        validateLoggedInUser(loggedInUser);

        return carRepository.findByRenterUserId(loggedInUser.getUserId())
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Important:
     * Filter by BOTH renter and status.
     * Not status only.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CarResponseDto> getCarsByStatus(CarStatus carStatus, AppUser loggedInUser) {
        validateLoggedInUser(loggedInUser);

        if (carStatus == null) {
            throw new BadRequestException("Car status is required");
        }

        return carRepository.findByRenterUserIdAndCarStatus(loggedInUser.getUserId(), carStatus)
                .stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Your updateCar ownership logic is kept.
     */
    @Override
    @Transactional
    public CarResponseDto updateCar(Long carId, CarRequestDto requestDto, AppUser loggedInUser) {
        validateLoggedInUser(loggedInUser);
        validateCarRequest(requestDto);

        Car car = findCarEntityById(carId);

        validateCarOwner(car, loggedInUser);

        if (carRepository.existsByPlateNumberAndCarIdNot(requestDto.getPlateNumber(), carId)) {
            throw new BadRequestException("Plate number already exists: " + requestDto.getPlateNumber());
        }

        mapRequestToEntity(car, requestDto);

        if (requestDto.getImageUrls() != null) {
            setCarImages(car, requestDto.getImageUrls());
        }

        Car updatedCar = carRepository.save(car);

        return mapToResponseDto(updatedCar);
    }

    @Override
    @Transactional
    public void deleteCar(Long carId, AppUser loggedInUser) {
        validateLoggedInUser(loggedInUser);

        Car car = findCarEntityById(carId);

        validateCarOwner(car, loggedInUser);

        carRepository.delete(car);
    }

    private Car findCarEntityById(Long carId) {
        return carRepository.findByCarId(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + carId));
    }

    private void mapRequestToEntity(Car car, CarRequestDto requestDto) {
        car.setBrand(requestDto.getBrand());
        car.setModel(requestDto.getModel());
        car.setBodyType(requestDto.getBodyType());
        car.setColor(requestDto.getColor());
        car.setMileage(requestDto.getMileage());
        car.setSeat(requestDto.getSeat());
        car.setYear(requestDto.getYear());
        car.setPlateNumber(requestDto.getPlateNumber());
        car.setDailyRate(requestDto.getDailyRate());
        car.setDescription(requestDto.getDescription());
        car.setFuelType(requestDto.getFuelType());
        car.setTransmission(requestDto.getTransmission());
        car.setCarStatus(requestDto.getCarStatus());
    }

    private void setCarImages(Car car, List<String> imageUrls) {
        if (car.getCarImages() == null) {
            car.setCarImages(new ArrayList<>());
        }

        car.getCarImages().clear();

        if (imageUrls == null || imageUrls.isEmpty()) {
            return;
        }

        for (String imageUrl : imageUrls) {
            if (imageUrl == null || imageUrl.trim().isEmpty()) {
                continue;
            }

            CarImage carImage = new CarImage();
            carImage.setCar(car);
            carImage.setImageUrl(imageUrl.trim());

            car.getCarImages().add(carImage);
        }
    }

    private CarResponseDto mapToResponseDto(Car car) {
        CarResponseDto dto = new CarResponseDto();

        dto.setCarId(car.getCarId());

        if (car.getRenter() != null) {
            dto.setRenterId(car.getRenter().getUserId());
            dto.setRenterUsername(car.getRenter().getUsername());
            dto.setRenterFullName(car.getRenter().getFullName());
        }

        dto.setBrand(car.getBrand());
        dto.setModel(car.getModel());
        dto.setBodyType(car.getBodyType());
        dto.setColor(car.getColor());
        dto.setMileage(car.getMileage());
        dto.setSeat(car.getSeat());
        dto.setYear(car.getYear());
        dto.setPlateNumber(car.getPlateNumber());
        dto.setDailyRate(car.getDailyRate());
        dto.setDescription(car.getDescription());
        dto.setFuelType(car.getFuelType());
        dto.setTransmission(car.getTransmission());
        dto.setCarStatus(car.getCarStatus());
        dto.setCreatedAt(car.getCreatedAt());
        dto.setUpdatedAt(car.getUpdatedAt());

        if (car.getCarImages() != null) {
            dto.setImageUrls(
                    car.getCarImages()
                            .stream()
                            .map(CarImage::getImageUrl)
                            .collect(Collectors.toList())
            );
        } else {
            dto.setImageUrls(new ArrayList<>());
        }

        return dto;
    }

    private void validateCarRequest(CarRequestDto requestDto) {
        if (requestDto == null) {
            throw new BadRequestException("Car request cannot be null");
        }

        if (isBlank(requestDto.getBrand())) {
            throw new BadRequestException("Brand is required");
        }

        if (isBlank(requestDto.getModel())) {
            throw new BadRequestException("Model is required");
        }

        if (isBlank(requestDto.getPlateNumber())) {
            throw new BadRequestException("Plate number is required");
        }

        if (requestDto.getDailyRate() == null) {
            throw new BadRequestException("Daily rate is required");
        }

        if (requestDto.getDailyRate().signum() < 0) {
            throw new BadRequestException("Daily rate cannot be negative");
        }

        if (requestDto.getMileage() != null && requestDto.getMileage() < 0) {
            throw new BadRequestException("Mileage cannot be negative");
        }

        if (requestDto.getSeat() != null && requestDto.getSeat() <= 0) {
            throw new BadRequestException("Seat must be greater than 0");
        }

        if (requestDto.getCarStatus() == null) {
            throw new BadRequestException("Car status is required");
        }
    }

    private void validateLoggedInUser(AppUser loggedInUser) {
        if (loggedInUser == null) {
            throw new BadRequestException("User is not logged in");
        }
    }

    private void validateCarOwner(Car car, AppUser loggedInUser) {
        if (car.getRenter() == null || loggedInUser == null) {
            throw new BadRequestException("Unable to verify car owner");
        }

        if (!car.getRenter().getUserId().equals(loggedInUser.getUserId())) {
            throw new BadRequestException("You are not allowed to access or modify this car");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}