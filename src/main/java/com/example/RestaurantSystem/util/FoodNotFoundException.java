package com.example.RestaurantSystem.util;

public class FoodNotFoundException extends RuntimeException {

    public FoodNotFoundException(String msg){
        super(msg);
    }
}
