package com.example.RestaurantSystem.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthenticationDTO {
    @NotEmpty
    private String email;

    private String password;
}
