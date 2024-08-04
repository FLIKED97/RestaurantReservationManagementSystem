package com.example.RestaurantSystem.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "seats")
    private int seats;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "table")
    private List<Reservation> reservations;
}
