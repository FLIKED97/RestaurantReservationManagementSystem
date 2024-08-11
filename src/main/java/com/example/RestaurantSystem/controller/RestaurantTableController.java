package com.example.RestaurantSystem.controller;

import com.example.RestaurantSystem.dto.RestaurantTableDTO;
import com.example.RestaurantSystem.services.RestaurantTableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/table")
public class RestaurantTableController {
    private final RestaurantTableService tableService;

//    @GetMapping("/tables")
//    public Optional<List<RestaurantTableDTO>> getAllTables() {
//        return tableService.getAllRestaurantTables();
//    }

    @PostMapping("/admin/create")
    public Optional<RestaurantTableDTO> createTable(@RequestBody RestaurantTableDTO restaurantTableDTO) {
        return tableService.createTable(restaurantTableDTO);
    }

    @PutMapping("/admin/update")
    public Optional<RestaurantTableDTO> updateTable(@RequestBody RestaurantTableDTO restaurantTableDTO) {
        return tableService.changeTable(restaurantTableDTO);
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteTable(@PathVariable int id) {
        tableService.deleteTable(id);
    }
    @GetMapping("/user/show")
    public List<RestaurantTableDTO> getAllTable(){
        return tableService.getAllTable();
    }
}
