package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.models.Statistics;
import com.example.RestaurantSystem.repositories.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class StaticsService {
    private final StatisticsRepository statisticsRepository;
}
