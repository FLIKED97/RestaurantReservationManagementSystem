package com.example.RestaurantSystem.controller;

import com.example.RestaurantSystem.dto.RestaurantTableDTO;
import com.example.RestaurantSystem.services.RestaurantTableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/table")
public class RestaurantTableController {
    private final RestaurantTableService tableService;

    @PostMapping("/create")
    public Optional<RestaurantTableDTO> createTable(@RequestBody RestaurantTableDTO restaurantTableDTO) {
        return tableService.createTable(restaurantTableDTO);
    }

    @PutMapping("/update")
    public Optional<RestaurantTableDTO> updateTable(@RequestBody RestaurantTableDTO restaurantTableDTO) {
        return tableService.changeTable(restaurantTableDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTable(@PathVariable int id) {
        tableService.deleteTable(id);
    }
}
