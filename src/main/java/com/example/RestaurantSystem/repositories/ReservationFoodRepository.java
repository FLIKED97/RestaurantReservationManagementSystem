package com.example.RestaurantSystem.repositories;

import com.example.RestaurantSystem.models.ReservationFood;
import com.example.RestaurantSystem.models.ReservationFoodKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationFoodRepository extends JpaRepository<ReservationFood, ReservationFoodKey> {
}
