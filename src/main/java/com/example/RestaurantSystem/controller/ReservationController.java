package com.example.RestaurantSystem.controller;

import com.example.RestaurantSystem.dto.ReservationDTO;
import com.example.RestaurantSystem.services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/create")
    public Optional<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.createReservation(reservationDTO);
    }

    @PutMapping("/update")
    public Optional<ReservationDTO> updateReservation(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.changeReservation(reservationDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
    }
}
