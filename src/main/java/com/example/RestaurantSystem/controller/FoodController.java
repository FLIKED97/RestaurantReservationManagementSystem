package com.example.RestaurantSystem.controller;

import com.example.RestaurantSystem.dto.FoodDTO;
import com.example.RestaurantSystem.services.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/admin/add")
    public FoodDTO getFood(@RequestBody FoodDTO foodDTO){
        return foodService.addFood(foodDTO);
    }
    @DeleteMapping("/admin/delete/{id}")
    public void deleteFood(@PathVariable int id) {
        foodService.deleteFood(id);
    }

    @GetMapping("/show")
    public List<FoodDTO> show(){
        return foodService.getAllFood();
    }
}
