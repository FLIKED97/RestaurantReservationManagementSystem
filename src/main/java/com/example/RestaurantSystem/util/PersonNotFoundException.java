package com.example.RestaurantSystem.util;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String  msg) {
        super(msg);
    }
}
