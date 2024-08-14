package com.example.RestaurantSystem.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {
    private String message;
    private int statusCode;
    private long timestamp;
}
