package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.repositories.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StaticsService {
    private final StatisticsRepository statisticsRepository;
}
