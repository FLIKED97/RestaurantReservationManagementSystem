package com.example.RestaurantSystem.dto;

import com.example.RestaurantSystem.models.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantTableDTO {

    @NotNull(message = "The number must not be null")
    @Positive(message = "The number must be positive")
    private int number;

    @NotNull(message = "The seats must not be null")
    @Positive(message = "The seats must be positive")
    private int seats;
    @JsonIgnore
    private List<ReservationDTO> reservations;
}