package com.example.RestaurantSystem.dto;

import com.example.RestaurantSystem.models.Reservation;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationDTO {

    private int id;

    private Timestamp reservationTime;

    @Enumerated(EnumType.STRING)
    private Reservation.Status status;

    private Timestamp createdAt;


    private RestaurantTableDTO table;


    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELLED
    }
}