package com.example.RestaurantSystem.dto;

import com.example.RestaurantSystem.models.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReservationDTO {

    private int id;

    @PastOrPresent(message = "The reservation time date cannot be in the future")
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