package com.example.RestaurantSystem.repositories;

import com.example.RestaurantSystem.models.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics,Integer> {
}
