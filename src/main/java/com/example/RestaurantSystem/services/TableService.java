package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.dto.RestaurantTableDTO;
import com.example.RestaurantSystem.models.RestaurantTable;
import com.example.RestaurantSystem.repositories.TableRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final ModelMapper modelMapper;

    public Optional<RestaurantTableDTO> createTable(RestaurantTableDTO restaurantTableDTO) {
        return Optional.ofNullable(modelMapper.map(restaurantTableDTO, RestaurantTable.class))
                .map(tableRepository::save)
                .map(savedTable -> modelMapper.map(savedTable, RestaurantTableDTO.class));
    }

    public Optional<RestaurantTableDTO> changeTable(RestaurantTableDTO restaurantTableDTO) {
        Optional<RestaurantTable> tableOptional = Optional.ofNullable(tableRepository.findByNumber(restaurantTableDTO.getNumber()));
        if (tableOptional.isPresent()) {
            RestaurantTable foundRestaurantTable = tableOptional.get();
            foundRestaurantTable.setNumber(restaurantTableDTO.getNumber());
            foundRestaurantTable.setSeats(restaurantTableDTO.getSeats());
            RestaurantTable updateRestaurantTable = tableRepository.save(foundRestaurantTable);
            return Optional.of(modelMapper.map(updateRestaurantTable, RestaurantTableDTO.class));
        }
        return Optional.empty();
    }

    public void deleteTable(int id) {
        tableRepository.deleteById(id);
    }
}
