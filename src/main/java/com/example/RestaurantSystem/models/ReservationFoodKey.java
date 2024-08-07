package com.example.RestaurantSystem.models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ReservationFoodKey implements Serializable {
    private int reservationId;
    private int foodId;

    // equals and hashCode
}
