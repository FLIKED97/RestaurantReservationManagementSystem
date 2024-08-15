package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.dto.AddFoodToReservationDTO;
import com.example.RestaurantSystem.dto.ReservationDTO;
import com.example.RestaurantSystem.models.Food;
import com.example.RestaurantSystem.models.Reservation;
import com.example.RestaurantSystem.models.ReservationFood;
import com.example.RestaurantSystem.models.RestaurantTable;
import com.example.RestaurantSystem.repositories.*;
import com.example.RestaurantSystem.security.PersonDetails;
import com.example.RestaurantSystem.util.FoodNotFoundException;
import com.example.RestaurantSystem.util.ReservationNotFound;
import com.example.RestaurantSystem.util.TableNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final FoodRepository foodRepository;
    private final RestaurantTableRepository tableRepository;
    private final ModelMapper modelMapper;
    private final ReservationFoodRepository reservationFoodRepository;
    private final EmailService emailService;

    public Optional<List<ReservationDTO>> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        if (reservations.isEmpty()) {
            throw new ReservationNotFound("No reservations found");
        }

        return Optional.of(reservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDTO.class))
                .collect(Collectors.toList()));
    }

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {

        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

            reservation.setCreatedAt(new Date());
            reservation.setPerson(personDetails.getPerson());
            RestaurantTable restaurantTable = tableRepository.findByNumber(reservation.getRestaurantTable().getNumber())
                    .orElseThrow(() -> new TableNotFoundException("Table with number " + reservation.getRestaurantTable().getNumber() + " not found"));
            reservation.setRestaurantTable(restaurantTable);
            reservationRepository.save(reservation);

            return modelMapper.map(reservation, ReservationDTO.class);
    }

    public ReservationDTO changeReservation(ReservationDTO reservationDTO) {
        Reservation foundReservation = reservationRepository.findById(reservationDTO.getId())
                .orElseThrow(() -> new ReservationNotFound("Reservation with id" + reservationDTO.getId() + " not found"));

            foundReservation.setReservationTime(reservationDTO.getReservationTime());
            foundReservation.setRestaurantTable(foundReservation.getRestaurantTable());
            Reservation updateReservation = reservationRepository.save(foundReservation);
            return modelMapper.map(updateReservation, ReservationDTO.class);

    }

    public void cancelReservation(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFound("Reservation not found with id " + id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

            if (reservation.getPerson().getId() == personDetails.getPerson().getId()) {
                reservation.setStatus(Reservation.Status.CANCELLED);
                reservationRepository.save(reservation);

                emailService.sendSimpleMessage(personDetails.getPerson().getEmail(), "Restaurant System", "Ваше бронювання було відмінено");
            }  else {
                throw new AccessDeniedException("Access denied. You cannot cancel reservations you did not create.");
            }
    }

    public void confirmReservation(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFound("Reservation not found with id " + id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        if (reservation.getPerson().getId() == personDetails.getPerson().getId()) {
            reservation.setStatus(Reservation.Status.CONFIRMED);
            reservationRepository.save(reservation);
            emailService.sendSimpleMessage(personDetails.getPerson().getEmail(), "Restaurant System", "Ваше бронювання було підтверджено");
        } else {
            throw new AccessDeniedException("Access denied. You cannot confirm reservations you did not create.");
        }
    }


    public Optional<ReservationDTO> addFoodToReservation(AddFoodToReservationDTO addFoodDTO) {
        Reservation reservation = reservationRepository.findById(addFoodDTO.getReservationId())
                .orElseThrow(() -> new ReservationNotFound("Reservation not found with id " + addFoodDTO.getReservationId()));

        List<ReservationFood> reservationFoods = addFoodDTO.getReservationFoods().stream().map(dto -> {
            Food food = foodRepository.findById(dto.getFoodId())
                    .orElseThrow(() -> new FoodNotFoundException("Food not found with id " + dto.getFoodId()));

            ReservationFood reservationFood = new ReservationFood();
            reservationFood.setReservation(reservation);
            reservationFood.setFood(food);
            reservationFood.setQuantity(dto.getQuantity());
            return reservationFood;
        }).collect(Collectors.toList());

        reservationFoodRepository.saveAll(reservationFoods);
        return Optional.ofNullable(modelMapper.map(reservation, ReservationDTO.class));
    }

}