package com.example.RestaurantSystem.dto;

import com.example.RestaurantSystem.models.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class PersonDTO {

    @NotEmpty(message = "The First Name must not be blank")
    @Size(min = 2, max = 100, message = "The First Name should be between 2 and 100 characters long")
    private String firstName;
    @NotEmpty(message = "The Last Name must not be blank")
    @Size(min = 2, max = 100, message = "The Last Name should be between 2 and 100 characters long")
    private String lastName;

    private String password;

    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

//    private String role;

//    private List<Reservation> reservations;
}
