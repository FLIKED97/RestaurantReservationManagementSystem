package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.dto.FoodDTO;
import com.example.RestaurantSystem.models.Food;
import com.example.RestaurantSystem.repositories.FoodRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    private final ModelMapper modelMapper;

    private FoodDTO convertToFoodDTO(Food food) {
        return modelMapper.map(food, FoodDTO.class);
    }

    private Food convertToFood(FoodDTO foodDTO) {
        return modelMapper.map(foodDTO, Food.class);
    }

    public FoodDTO addFood(FoodDTO foodDTO) {
        Food food = convertToFood(foodDTO);
        food.setCreatedAt(new Date());
        Food savedFood = foodRepository.save(food);
        return convertToFoodDTO(savedFood);
    }

    public void deleteFood(int id) {
        foodRepository.deleteById(id);
    }
    public List<FoodDTO> getAllFood() {
        return foodRepository.findAll().stream().map(this::convertToFoodDTO)
                .collect(Collectors.toList());
    }
}
