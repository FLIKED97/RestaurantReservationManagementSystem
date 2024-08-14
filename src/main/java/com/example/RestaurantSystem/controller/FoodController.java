package com.example.RestaurantSystem.controller;

import com.example.RestaurantSystem.dto.FoodDTO;
import com.example.RestaurantSystem.services.FoodService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/admin/add")
    public ResponseEntity<?> addFood(@RequestBody @Valid FoodDTO foodDTO,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Обробка помилок валідації
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }

        FoodDTO savedFood = foodService.addFood(foodDTO);
        return ResponseEntity.ok(savedFood);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteFood(@PathVariable int id) {
        foodService.deleteFood(id);
    }

    @GetMapping("/user/show")
    public List<FoodDTO> show() {
        return foodService.getAllFood();
    }
}

