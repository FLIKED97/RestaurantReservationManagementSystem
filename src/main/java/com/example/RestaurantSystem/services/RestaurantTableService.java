package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.repositories.RestaurantTableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantTableService {
    private final RestaurantTableRepository tableRepository;
}
