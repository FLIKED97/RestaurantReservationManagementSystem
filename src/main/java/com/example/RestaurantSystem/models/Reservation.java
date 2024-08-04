package com.example.RestaurantSystem.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "Reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reservation_time")
    private Timestamp reservationTime;


    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELLED
    }
}
