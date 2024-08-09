package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.dto.AddFoodToReservationDTO;
import com.example.RestaurantSystem.dto.ReservationDTO;
import com.example.RestaurantSystem.models.Reservation;
import com.example.RestaurantSystem.models.ReservationFood;
import com.example.RestaurantSystem.repositories.*;
import com.example.RestaurantSystem.security.PersonDetails;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final FoodRepository foodRepository;
    private final PersonRepository personRepository;
    private final RestaurantTableRepository tableRepository;
    private final ModelMapper modelMapper;

    private final ReservationFoodRepository reservationFoodRepository;

    public Optional<List<ReservationDTO>> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return Optional.of(reservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
                .collect(Collectors.toList()));
    }

    public Optional<ReservationDTO> createReservation(ReservationDTO reservationDTO) {

        Optional<Reservation> reservationOptional = Optional.ofNullable(modelMapper.map(reservationDTO, Reservation.class));
        if (reservationOptional.isPresent()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
            Reservation reservation = reservationOptional.get();
            reservation.setCreatedAt(new Date());
            reservation.setPerson(personDetails.getPerson());
            reservationRepository.save(reservation);
            return Optional.ofNullable(modelMapper.map(reservation, ReservationDTO.class));
        }
        return Optional.empty();
    }

    public Optional<ReservationDTO> changeReservation(ReservationDTO reservationDTO) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationDTO.getId());
        if (reservationOptional.isPresent()) {
            Reservation foundReservation = reservationOptional.get();
            //foundReservation.setQuantity(reservationDTO.getQuantity());
            foundReservation.setReservationTime(reservationDTO.getReservationTime());
            foundReservation.setRestaurantTable(foundReservation.getRestaurantTable());
            Reservation updateReservation = reservationRepository.save(foundReservation);
            return Optional.ofNullable(modelMapper.map(updateReservation, ReservationDTO.class));
        }
        return Optional.empty();
    }

    public void cancelReservation(int id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);

        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            reservation.setStatus(Reservation.Status.CANCELLED);
            reservationRepository.save(reservation);
        } else {
            throw new NoSuchElementException("Reservation not found with id " + id);
        }
    }

    public Optional<ReservationDTO> addFoodToReservation(AddFoodToReservationDTO addFoodDTO) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(addFoodDTO.getReservationId());
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            List<ReservationFood> reservationFoods = addFoodDTO.getReservationFoods().stream().map(dto -> {
                ReservationFood reservationFood = new ReservationFood();
                reservationFood.setReservation(reservation);
                reservationFood.setFood(foodRepository.findById(dto.getFoodId())
                        .orElseThrow(() -> new NoSuchElementException("Food not found")));
                reservationFood.setQuantity(dto.getQuantity());
                return reservationFood;
            }).collect(Collectors.toList());

            reservationFoodRepository.saveAll(reservationFoods);
            return Optional.ofNullable(modelMapper.map(reservation, ReservationDTO.class));
        }
        return Optional.empty();
    }
}