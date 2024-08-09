package com.example.RestaurantSystem.dto;

import com.example.RestaurantSystem.models.Reservation;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationDTO {

    private int id;

    private Timestamp reservationTime;

//    private int quantity;

    @Enumerated(EnumType.STRING)
    private Reservation.Status status;


    private Timestamp createdAt;


//    private PersonDTO personDTO;

    private RestaurantTableDTO table;

//    private FoodDTO food;

    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELLED
    }
}