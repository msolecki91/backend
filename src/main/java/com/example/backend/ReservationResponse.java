package com.example.backend;
import java.time.LocalDateTime;

public class ReservationResponse {
    private Long id;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Long userId;
    private Long carId;

    public ReservationResponse(Long id, LocalDateTime dateFrom, LocalDateTime dateTo, Long userId, Long carId) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.userId = userId;
        this.carId = carId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}