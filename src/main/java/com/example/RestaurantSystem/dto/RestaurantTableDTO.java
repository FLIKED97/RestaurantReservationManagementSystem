package com.example.RestaurantSystem.dto;

import com.example.RestaurantSystem.models.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantTableDTO {

    private int number;

    private int seats;
    @JsonIgnore
    private List<ReservationDTO> reservations;
}