package com.example.RestaurantSystem.services;


import com.example.RestaurantSystem.dto.RestaurantTableDTO;
import com.example.RestaurantSystem.models.RestaurantTable;
import com.example.RestaurantSystem.repositories.RestaurantTableRepository;
import com.example.RestaurantSystem.util.TableNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantTableService {
    private final RestaurantTableRepository restaurantTableRepository;

    private final ModelMapper modelMapper;

    public Optional<RestaurantTableDTO> createTable(RestaurantTableDTO restaurantTableDTO) {
        return Optional.ofNullable(modelMapper.map(restaurantTableDTO, RestaurantTable.class))
                .map(restaurantTableRepository::save)
                .map(savedTable -> modelMapper.map(savedTable, RestaurantTableDTO.class));
    }

    public Optional<RestaurantTableDTO> changeTable(RestaurantTableDTO restaurantTableDTO) {
        Optional<RestaurantTable> restaurantTableOptional = Optional.ofNullable(restaurantTableRepository.findByNumber(restaurantTableDTO.getNumber()))
                .orElseThrow(()-> new TableNotFoundException("Table with number " + restaurantTableDTO.getNumber() + " not found"));
        if (restaurantTableOptional.isPresent()) {
            RestaurantTable foundRestaurantTable = restaurantTableOptional.get();
            RestaurantTable updateRestaurantTable = restaurantTableRepository.save(foundRestaurantTable);
            return Optional.ofNullable(modelMapper.map(updateRestaurantTable, RestaurantTableDTO.class));
        }
        return Optional.empty();
    }

    public void deleteTable(int id) {
        restaurantTableRepository.deleteById(id);
    }

    public List<RestaurantTableDTO> getAllTable() {
        return restaurantTableRepository.findAll().stream().map(this::convertToRestaurantTableDTO)
                .collect(Collectors.toList());
    }

    private RestaurantTableDTO convertToRestaurantTableDTO(RestaurantTable restaurantTable){
        return modelMapper.map(restaurantTable, RestaurantTableDTO.class);
    }
    private RestaurantTable convertToRestaurantTable(RestaurantTableDTO restaurantTableDTO){
        return modelMapper.map(restaurantTableDTO, RestaurantTable.class);
    }
}