package com.example.backend.services;

import com.example.backend.entities.User;
import com.example.backend.repositories.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import static org.springframework.util.ObjectUtils.isEmpty;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean initUsers() {
        final List<User> users = new ArrayList<>();
        users.add(new User("Adam", "Kowalski", 25));
        users.add(new User("Michał", "Nowak", 34));
        users.add(new User("Martyna", "Kowalczyk", 26));
        users.add(new User("Ewa", "Nowak", 40));
        users.add(new User("Robert", "Zielony", 51));

        userRepository.saveAll(users);
        return true;
    }

    @EventListener(ApplicationReadyEvent.class)
    public boolean createUsersIfRepositoryIsEmpty() {
        if (isEmpty(getAllUsers())) {
            if(initUsers()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}