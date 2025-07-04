package com.example.RestaurantSystem.repositories;

import com.example.RestaurantSystem.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

}
