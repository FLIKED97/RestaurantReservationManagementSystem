package com.example.RestaurantSystem.controller;

import com.example.RestaurantSystem.dto.RestaurantTableDTO;
import com.example.RestaurantSystem.services.RestaurantTableService;
import com.example.RestaurantSystem.util.TableNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/table")
public class RestaurantTableController {
    private final RestaurantTableService tableService;

    @PostMapping("/admin/create")
    public ResponseEntity<?> createTable(@RequestBody @Valid RestaurantTableDTO restaurantTableDTO,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }

        return ResponseEntity.ok(tableService.createTable(restaurantTableDTO));
    }

    @PutMapping("/admin/update")
    public ResponseEntity<?> updateTable(@RequestBody @Valid RestaurantTableDTO restaurantTableDTO,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }

        return ResponseEntity.ok(tableService.changeTable(restaurantTableDTO));
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> deleteTable(@PathVariable int id) {
        try {
            tableService.deleteTable(id);
            return ResponseEntity.ok("Table with id " + id + " successfully deleted.");
        } catch (TableNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the table.");
        }
    }

    @GetMapping("/user/show")
    public List<RestaurantTableDTO> getAllTable(){
        return tableService.getAllTable();
    }
}
