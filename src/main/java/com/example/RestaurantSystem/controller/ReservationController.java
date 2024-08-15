package com.example.RestaurantSystem.controller;

import com.example.RestaurantSystem.dto.AddFoodToReservationDTO;
import com.example.RestaurantSystem.dto.ReservationDTO;
import com.example.RestaurantSystem.services.ReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> createReservation(@RequestBody @Valid ReservationDTO reservationDTO,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }

        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);

        return ResponseEntity.ok(createdReservation);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateReservation(@RequestBody @Valid ReservationDTO reservationDTO,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        ReservationDTO updatedReservationDTO = reservationService.changeReservation(reservationDTO);

        return ResponseEntity.ok(updatedReservationDTO);
    }


    @PutMapping("/confirm/{id}")
    public ResponseEntity<ReservationDTO> confirmReservation(@PathVariable int id) {
        reservationService.confirmReservation(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable int id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/food")
    public ResponseEntity<?> addFoodToReservation(@RequestBody @Valid AddFoodToReservationDTO addFoodDTO,
                                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        Optional<ReservationDTO> responseDTO = reservationService.addFoodToReservation(addFoodDTO);
        return responseDTO.map(reservationDTO -> new ResponseEntity<>(reservationDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
