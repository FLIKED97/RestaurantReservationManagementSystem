package com.example.RestaurantSystem.controller;

import com.example.RestaurantSystem.dto.FoodDTO;
import com.example.RestaurantSystem.services.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/add")
    public FoodDTO getFood(@RequestBody FoodDTO foodDTO){
        return foodService.addFood(foodDTO);
    }
}
