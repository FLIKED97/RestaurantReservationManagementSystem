package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
}
