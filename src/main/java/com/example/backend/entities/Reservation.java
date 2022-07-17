package com.example.backend.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    public Reservation() {
    }

    public Reservation(Long id, LocalDateTime dateFrom, LocalDateTime dateTo, User user, Car car) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.user = user;
        this.car = car;
    }

    public Reservation(LocalDateTime dateFrom, LocalDateTime dateTo, User user, Car car) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.user = user;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}