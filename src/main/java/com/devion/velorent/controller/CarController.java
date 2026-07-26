package com.devion.velorent.controller;

import com.devion.velorent.dto.car.CarRequestDto;
import com.devion.velorent.dto.car.CarResponseDto;
import com.devion.velorent.entity.AppUser;
import com.devion.velorent.enums.CarStatus;
import com.devion.velorent.service.CarService;
import com.devion.velorent.service.CurrentUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Listener/controller for Car APIs.
 *
 * All APIs are renter-scoped.
 * Logged-in renter can only access their own cars.
 */
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    private final CurrentUserService currentUserService;

    public CarController(
            CarService carService,
            CurrentUserService currentUserService
    ) {
        this.carService = carService;
        this.currentUserService = currentUserService;
    }

    /**
     * POST /api/cars
     * Create car under logged-in renter.
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
     * GET /api/cars
     *
     * Important:
     * This returns only logged-in renter's cars.
     * It does NOT return all cars from all renters.
     */
    @GetMapping
    public ResponseEntity<List<CarResponseDto>> getAllCars(HttpSession session) {
        AppUser loggedInUser = getLoggedInUser(session);

        return ResponseEntity.ok(carService.getAllCars(loggedInUser));
    }

    /**
     * GET /api/cars/my
     *
     * Same behavior as GET /api/cars.
     * You can keep this for frontend readability.
     */
    @GetMapping("/my")
    public ResponseEntity<List<CarResponseDto>> getMyCars(HttpSession session) {
        AppUser loggedInUser = getLoggedInUser(session);

        return ResponseEntity.ok(carService.getMyCars(loggedInUser));
    }

    /**
     * GET /api/cars/{carId}
     *
     * Only owner can view car detail.
     */
    @GetMapping("/{carId}")
    public ResponseEntity<CarResponseDto> getCarById(
            @PathVariable Long carId,
            HttpSession session) {

        AppUser loggedInUser = getLoggedInUser(session);

        return ResponseEntity.ok(carService.getCarById(carId, loggedInUser));
    }

    /**
     * GET /api/cars/status/{carStatus}
     *
     * Returns cars under logged-in renter filtered by status.
     */
    @GetMapping("/status/{carStatus}")
    public ResponseEntity<List<CarResponseDto>> getCarsByStatus(
            @PathVariable CarStatus carStatus,
            HttpSession session) {

        AppUser loggedInUser = getLoggedInUser(session);

        return ResponseEntity.ok(carService.getCarsByStatus(carStatus, loggedInUser));
    }

    /**
     * PUT /api/cars/{carId}
     *
     * Only owner can update.
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
     * DELETE /api/cars/{carId}
     *
     * Only owner can delete.
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
     * Current AuthController stores AppUser under "username".
     */
    private AppUser getLoggedInUser(HttpSession session) {
        return currentUserService.getCurrentUser(session);
    }
}