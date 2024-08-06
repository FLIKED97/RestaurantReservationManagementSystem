package com.example.RestaurantSystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@jakarta.persistence.Table(name = "Table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "seats")
    private int seats;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "table")
    @JsonIgnore
    private List<Reservation> reservations;
}
