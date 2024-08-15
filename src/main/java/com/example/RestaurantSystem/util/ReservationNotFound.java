package com.example.RestaurantSystem.util;

public class ReservationNotFound extends RuntimeException {
    public ReservationNotFound(String msg) {
        super(msg);
    }
}
