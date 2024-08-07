package com.example.RestaurantSystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


//    @Column(name = "quantity")
//    private int quantity;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;

    @ManyToOne
    @JoinColumn(name = "restaurant_table_id")
    @JsonIgnore
    private RestaurantTable restaurantTable;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservationFood> reservationFoods = new ArrayList<>();

    public enum Status {
        PENDING,
        CONFIRMED,
        CANCELLED
    }
}