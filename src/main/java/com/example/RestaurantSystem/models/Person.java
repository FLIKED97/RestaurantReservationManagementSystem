package com.example.RestaurantSystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Person")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    @NotEmpty(message = "The First Name must not be blank")
    @Size(min = 2, max = 100, message = "The First Name should be between 2 and 100 characters long")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "The Last Name must not be blank")
    @Size(min = 2, max = 100, message = "The Last Name should be between 2 and 100 characters long")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private List<Reservation> reservations;
}
