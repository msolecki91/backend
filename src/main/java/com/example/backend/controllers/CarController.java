package com.example.backend.controllers;

import com.example.backend.entities.Car;
import com.example.backend.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private ReservationService reservationService;

    public CarController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/unreserved")
    public ResponseEntity<List<Car>> getAllUnreservedCars() {
        return ResponseEntity.ok(reservationService.getAllUnreservedCars());
    }

}