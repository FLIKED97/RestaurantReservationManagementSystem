package com.example.RestaurantSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddFoodToReservationDTO {
    private int reservationId;
    private List<ReservationFoodCreateDTO> reservationFoods;

    // Getters and Setters
}

