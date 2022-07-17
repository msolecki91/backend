package com.example.backend.controllers;

import com.example.backend.ReservationResponse;
import com.example.backend.entities.Reservation;
import com.example.backend.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/by/user/{id}")
    public ResponseEntity<List<ReservationResponse>> getAllReservationsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getAllReservationsForUser(id));
    }

    @GetMapping("/by/user/{id}/actual")
    public  ResponseEntity<List<ReservationResponse>> getAllActualReservations(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getAllActualReservationsForUser(id));
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationResponse reservation) {
        if (reservationService.saveReservation(reservation)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        if(!reservationService.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            if(reservationService.deleteReservation(id)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        }
    }
}