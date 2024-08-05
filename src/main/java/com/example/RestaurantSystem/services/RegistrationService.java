package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.models.Person;
import com.example.RestaurantSystem.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        person.setCreatedAt(new Date());
        personRepository.save(person);
    }
}
