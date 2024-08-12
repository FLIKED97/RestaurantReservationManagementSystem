package com.example.RestaurantSystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Food")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "The  Name must not be blank")
    @Size(min = 2, max = 100, message = "The  Name should be between 2 and 100 characters long")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    @NotEmpty(message = "The description must not be blank")
    @Size(min = 2, max = 1000, message = "The description should be between 2 and 1000 characters long")
    private String description;

    @Column(name = "price")
    @NotNull(message = "The price must not be null")
    @Positive(message = "The price must be positive")
    private BigDecimal price;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "food")
    @JsonIgnore
    private List<ReservationFood> ReservationFood;
}
