package com.example.RestaurantSystem.util;

public class TableNotFoundException extends RuntimeException {

    public TableNotFoundException(String msg) {
        super(msg);
    }
}
