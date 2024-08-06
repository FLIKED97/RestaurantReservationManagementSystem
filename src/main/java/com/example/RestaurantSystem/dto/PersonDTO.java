package com.example.RestaurantSystem.dto;

import com.example.RestaurantSystem.models.Reservation;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class PersonDTO {

    private String firstName;

    private String lastName;

    private String password;

    private String email;

//    private String role;

//    private List<Reservation> reservations;
}
