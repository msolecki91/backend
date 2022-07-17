package com.example.backend.services;

import com.example.backend.entities.Car;
import com.example.backend.repositories.CarRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getCar(Long id) {
        return carRepository.findById(id).get();
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public boolean initCars() {
        final List<Car> cars = new ArrayList<>();
        cars.add(new Car("Skoda", "Fabia", "SB12345", Car.Color.BLACK));
        cars.add(new Car("Volkswagen", "Polo", "SB12346", Car.Color.WHITE));
        cars.add(new Car("Audi", "A4", "SB12347", Car.Color.GOLD));
        cars.add(new Car("Toyota", "Avensis", "SB12349", Car.Color.SILVER));
        cars.add(new Car("Toyota", "Supra", "SB12350", Car.Color.RED));
        cars.add(new Car("BMW", "X1", "SB12351", Car.Color.PURPLE));
        cars.add(new Car("BMW", "E60", "SB12352", Car.Color.BLACK));
        cars.add(new Car("Dodge", "Challenger", "SB12353", Car.Color.GREEN));
        cars.add(new Car("Ford Mustang", "GT", "SB12354", Car.Color.BLUE));

        carRepository.saveAll(cars);
        return true;
    }

    @EventListener(ApplicationReadyEvent.class)
    public boolean createUsersIfRepositoryIsEmpty() {
        if (isEmpty(getAllCars())) {
            return initCars();
        }
        return false;
    }
}