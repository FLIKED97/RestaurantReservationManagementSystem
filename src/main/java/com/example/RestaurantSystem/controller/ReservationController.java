package com.example.RestaurantSystem.controller;

import com.example.RestaurantSystem.dto.AddFoodToReservationDTO;
import com.example.RestaurantSystem.dto.ReservationDTO;
import com.example.RestaurantSystem.services.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/getAll")
    public Optional<List<ReservationDTO>> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping("/create")
    public Optional<ReservationDTO> createReservation(@RequestBody @Valid ReservationDTO reservationDTO) {
        return reservationService.createReservation(reservationDTO);
    }

    @PutMapping("/update")
    public Optional<ReservationDTO> updateReservation(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.changeReservation(reservationDTO);
    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity<ReservationDTO> confirmReservation(@PathVariable int id) {
        reservationService.confirmReservation(id);
        return ResponseEntity.ok().build();
    }

    //Думаю тут ліпше підійде putMapping бо по факту ми не видаляємо повністю а просто змінюємо статус хотя не впевнений
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable int id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/food")
    public ResponseEntity<ReservationDTO> addFoodToReservation(@RequestBody AddFoodToReservationDTO addFoodDTO) {
        Optional<ReservationDTO> responseDTO = reservationService.addFoodToReservation(addFoodDTO);
        return responseDTO.map(reservationDTO -> new ResponseEntity<>(reservationDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
