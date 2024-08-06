package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.dto.ReservationDTO;
import com.example.RestaurantSystem.models.Reservation;
import com.example.RestaurantSystem.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    private final ModelMapper modelMapper;

    public Optional<ReservationDTO> createReservation(ReservationDTO reservationDTO) {
        Optional<Reservation> reservationOptional = Optional.ofNullable(modelMapper.map(reservationDTO, Reservation.class));
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            reservation.setCreatedAt(new Date());
            reservationRepository.save(reservation);
            return Optional.ofNullable(modelMapper.map(reservation, ReservationDTO.class));
        }
        return Optional.empty();
    }

    public Optional<ReservationDTO> changeReservation(ReservationDTO reservationDTO) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationDTO.getId());
        if (reservationOptional.isPresent()) {
            Reservation foundReservation = reservationOptional.get();
            foundReservation.setQuantity(reservationDTO.getQuantity());
            foundReservation.setReservationTime(reservationDTO.getReservationTime());
            foundReservation.setRestaurantTable(foundReservation.getRestaurantTable());
            foundReservation.setFood(foundReservation.getFood());
            Reservation updateReservation = reservationRepository.save(foundReservation);
            return Optional.ofNullable(modelMapper.map(updateReservation, ReservationDTO.class));
        }
        return Optional.empty();
    }

    public void deleteReservation(int id) {
        reservationRepository.deleteById(id);
    }
}
