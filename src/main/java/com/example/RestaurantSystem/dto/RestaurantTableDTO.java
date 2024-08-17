package com.example.RestaurantSystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

//Дописав анотації NonNull бо не хоче адекватно створювати конструктор
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RestaurantTableDTO {

    @NotNull(message = "The number must not be null")
    @Positive(message = "The number must be positive")
    @NonNull
    private int number;

    @NotNull(message = "The seats must not be null")
    @Positive(message = "The seats must be positive")
    @NonNull
    private int seats;
    @JsonIgnore
    private List<ReservationDTO> reservations;

}