package com.example.RestaurantSystem.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Statistics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "total_reservations")
    private int totalReservations;

    @Column(name = "total_confirmed")
    private int totalConfirmed;

    @Column(name = "total_cancelled")
    private int totalCancelled;

    @Column(name = "created_at")
    private Timestamp createdAt;
}
