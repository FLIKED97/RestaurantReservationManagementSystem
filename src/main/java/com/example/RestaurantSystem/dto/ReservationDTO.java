package com.example.RestaurantSystem.dto;

import com.example.RestaurantSystem.models.Food;
import com.example.RestaurantSystem.models.Person;
import com.example.RestaurantSystem.models.Reservation;
import com.example.RestaurantSystem.models.RestaurantTable;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationDTO {

    private Timestamp reservationTime;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Reservation.Status status;


    private Timestamp createdAt;


    private PersonDTO personDTO;

    private RestaurantTable table;


    private Food food;

    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELLED
    }
}
