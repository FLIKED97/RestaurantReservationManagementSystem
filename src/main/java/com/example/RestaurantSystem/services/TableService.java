package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.repositories.TableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
}
