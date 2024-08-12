package com.example.RestaurantSystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Table(name = "reservation_food")
@Data
public class ReservationFood {
    @EmbeddedId
    private ReservationFoodKey id = new ReservationFoodKey();

    @ManyToOne
    @MapsId("reservationId")
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    private Food food;

    @NotNull(message = "The quantity must not be null")
    @Positive(message = "The quantity must be positive")
    private int quantity;
}
