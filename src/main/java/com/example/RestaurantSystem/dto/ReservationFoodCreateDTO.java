package com.example.RestaurantSystem.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ReservationFoodCreateDTO {

    private int foodId;
    @NotNull(message = "The quantity must not be null")
    @Positive(message = "The quantity must be positive")
    private int quantity;

    // Getters and Setters
}
