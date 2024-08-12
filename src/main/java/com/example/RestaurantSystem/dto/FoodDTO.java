package com.example.RestaurantSystem.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FoodDTO {
    @NotEmpty(message = "The  Name must not be blank")
    @Size(min = 2, max = 100, message = "The  Name should be between 2 and 100 characters long")
    private String name;

    @NotEmpty(message = "The description must not be blank")
    @Size(min = 2, max = 1000, message = "The description should be between 2 and 1000 characters long")
    private String description;

    @NotNull(message = "The price must not be null")
    @Positive(message = "The price must be positive")
    private BigDecimal price;
}
