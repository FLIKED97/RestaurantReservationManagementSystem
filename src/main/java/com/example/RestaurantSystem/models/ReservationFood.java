package com.example.RestaurantSystem.models;

import jakarta.persistence.*;
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

    private int quantity;
}
