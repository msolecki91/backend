package com.example.backend.entities;

import javax.persistence.*;

@Entity
@Table(name="cars")
public class Car {

    public enum Color {BLACK, GREEN, RED, BLUE, WHITE, GOLD, SILVER, PURPLE}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String manufacturer;
    private String model;
    private String licensePlateNr;
    private Color color;

    public Car() {
    }

    public Car(String manufacturer, String model, String licensePlateNr, Color color) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.licensePlateNr = licensePlateNr;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlateNr() {
        return licensePlateNr;
    }

    public void setLicensePlateNr(String licensePlateNr) {
        this.licensePlateNr = licensePlateNr;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}