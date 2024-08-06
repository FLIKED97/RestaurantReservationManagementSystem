package com.example.RestaurantSystem.repositories;


import com.example.RestaurantSystem.models.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {
}
