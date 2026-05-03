package com.devion.velorent.controller;

import com.devion.velorent.dto.car.CarRequestDto;
import com.devion.velorent.dto.car.CarResponseDto;
import com.devion.velorent.entity.AppUser;
import com.devion.velorent.enums.CarStatus;
import com.devion.velorent.service.CarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Listener/controller for Car APIs.
 */
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * Create car.
     * Renter is taken from logged-in session.
     *
     * POST /api/cars
     */
    @PostMapping
    public ResponseEntity<CarResponseDto> createCar(
            @RequestBody CarRequestDto requestDto,
            HttpSession session) {

        AppUser loggedInUser = getLoggedInUser(session);

        CarResponseDto response = carService.createCar(requestDto, loggedInUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get all cars.
     *
     * GET /api/cars
     */
    @GetMapping
    public ResponseEntity<List<CarResponseDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    /**
     * Get cars owned by current logged-in user.
     *
     * GET /api/cars/my
     */
    @GetMapping("/my")
    public ResponseEntity<List<CarResponseDto>> getMyCars(HttpSession session) {
        AppUser loggedInUser = getLoggedInUser(session);

        return ResponseEntity.ok(carService.getMyCars(loggedInUser));
    }

    /**
     * Get car by id.
     *
     * GET /api/cars/{carId}
     */
    @GetMapping("/{carId}")
    public ResponseEntity<CarResponseDto> getCarById(@PathVariable Long carId) {
        return ResponseEntity.ok(carService.getCarById(carId));
    }

    /**
     * Get cars by status.
     *
     * Example:
     * GET /api/cars/status/AVAILABLE
     */
    @GetMapping("/status/{carStatus}")
    public ResponseEntity<List<CarResponseDto>> getCarsByStatus(@PathVariable CarStatus carStatus) {
        return ResponseEntity.ok(carService.getCarsByStatus(carStatus));
    }

    /**
     * Update car.
     * Only owner can update.
     *
     * PUT /api/cars/{carId}
     */
    @PutMapping("/{carId}")
    public ResponseEntity<CarResponseDto> updateCar(
            @PathVariable Long carId,
            @RequestBody CarRequestDto requestDto,
            HttpSession session) {

        AppUser loggedInUser = getLoggedInUser(session);

        return ResponseEntity.ok(carService.updateCar(carId, requestDto, loggedInUser));
    }

    /**
     * Delete car.
     * Only owner can delete.
     *
     * DELETE /api/cars/{carId}
     */
    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(
            @PathVariable Long carId,
            HttpSession session) {

        AppUser loggedInUser = getLoggedInUser(session);

        carService.deleteCar(carId, loggedInUser);

        return ResponseEntity.noContent().build();
    }

    /**
     * Helper method to read logged-in AppUser from session.
     *
     * Your AuthController currently stores AppUser under key "username":
     * session.setAttribute("username", user);
     */
    private AppUser getLoggedInUser(HttpSession session) {
        return (AppUser) session.getAttribute("username");
    }
}