package com.example.RestaurantSystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "restaurant_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    @NotNull(message = "The number must not be null")
    @Positive(message = "The number must be positive")
    private int number;

    @Column(name = "seats")
    @NotNull(message = "The seats must not be null")
    @Positive(message = "The seats must be positive")
    private int seats;

//    @Column(name = "created_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;

    @OneToMany(mappedBy = "restaurantTable")
    @JsonIgnore
    private List<Reservation> reservations;
}
