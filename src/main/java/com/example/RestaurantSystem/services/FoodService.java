package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.repositories.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
}
