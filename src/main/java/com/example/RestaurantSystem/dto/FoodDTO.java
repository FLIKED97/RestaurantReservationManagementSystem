package com.example.RestaurantSystem.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FoodDTO {
    private String name;

    private String description;

    private BigDecimal price;
}
