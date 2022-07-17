package com.example.backend.services;

import com.example.backend.ReservationResponse;
import com.example.backend.entities.Car;
import com.example.backend.entities.Reservation;
import com.example.backend.repositories.ReservationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CarService carService;
    private final UserService userService;


    public ReservationService(ReservationRepository reservationRepository, CarService carService, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.carService = carService;
        this.userService = userService;
    }

    public List<ReservationResponse> getAllReservationsForUser(Long id) {
        final List<Reservation> reservations = reservationRepository.findByUserId(id);
        final List<ReservationResponse> reservationResponses = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationResponses.add(convertToResponse(reservation));
        }
        return reservationResponses;
    }

    public List<Reservation> getAllActualReservations() {
        return reservationRepository.findAll().stream().filter(v -> (v.getDateFrom().isBefore(LocalDateTime.now())
                && v.getDateTo().isAfter(LocalDateTime.now()))).collect(Collectors.toList());
    }

    public List<ReservationResponse> getAllActualReservationsForUser(Long id) {
        final List<Reservation> reservations = reservationRepository.findByUserId(id);
        final List<ReservationResponse> reservationResponses = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationResponses.add(convertToResponse(reservation));
        }

        return reservationResponses.stream().filter(v -> (v.getDateFrom().isBefore(LocalDateTime.now())
                && v.getDateTo().isAfter(LocalDateTime.now()))).collect(Collectors.toList());
    }

    public boolean saveReservation(ReservationResponse reservation) {
        reservationRepository.save(new Reservation(reservation.getDateFrom(), reservation.getDateTo(),
                userService.getUser(reservation.getUserId()), carService.getCar(reservation.getCarId())));
        return true;
    }

    public boolean existsById(Long id) {
        return reservationRepository.existsById(id);
    }

    public boolean deleteReservation(Long id) {
        reservationRepository.deleteById(id);
        return true;
    }

    public List<Car> getAllUnreservedCars() {
        final List<Car> allCars = carService.getAllCars();
        final List<Reservation> reservations = getAllActualReservations();
        final List<Long> reservedCarIds = new ArrayList<>();

        for(Reservation reservation : reservations) {
            reservedCarIds.add(reservation.getCar().getId());
        }

        return allCars.stream().filter(v ->
                !reservedCarIds.contains(v.getId())).collect(Collectors.toList());
    }

    private ReservationResponse convertToResponse(Reservation reservation) {
        return new ReservationResponse(reservation.getId(), reservation.getDateFrom(), reservation.getDateTo(),
                reservation.getUser().getId(), reservation.getCar().getId());
    }
}