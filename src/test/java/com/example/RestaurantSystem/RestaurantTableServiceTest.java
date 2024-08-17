package com.example.RestaurantSystem;

import com.example.RestaurantSystem.dto.RestaurantTableDTO;
import com.example.RestaurantSystem.models.RestaurantTable;
import com.example.RestaurantSystem.repositories.RestaurantTableRepository;
import com.example.RestaurantSystem.services.RestaurantTableService;
import com.example.RestaurantSystem.util.TableNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class RestaurantTableServiceTest {
    private RestaurantTableService restaurantTableService;

    @Mock
    private RestaurantTableRepository mockRepository;

    @BeforeEach
    public void init() {
        restaurantTableService = new RestaurantTableService(mockRepository,new ModelMapper());
    }

    @Test
    public void testGetAllTables_EmptyList() {
        List<RestaurantTable> emptyList = Collections.emptyList();
        Mockito.when(mockRepository.findAll()).thenReturn(emptyList);

        List<RestaurantTableDTO> allTables = restaurantTableService.getAllTable();
        assertEquals(Collections.emptyList(), allTables);

        Mockito.verify(mockRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetAllTables_NotEmptyList() {
        List<RestaurantTable> tables = new ArrayList<>();
        tables.add(new RestaurantTable(1, 4));
        tables.add(new RestaurantTable(2, 6));
        Mockito.when(mockRepository.findAll()).thenReturn(tables);

        List<RestaurantTableDTO> allTables = restaurantTableService.getAllTable();
        assertEquals(2, allTables.size());

        Mockito.verify(mockRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testCreateTableSuccess() {
        RestaurantTableDTO restaurantTableDTO = new RestaurantTableDTO(1,4);
        RestaurantTable expectedTable = new RestaurantTable(restaurantTableDTO.getNumber(), restaurantTableDTO.getSeats());

        Mockito.when(mockRepository.save(expectedTable)).thenReturn(expectedTable);

        Optional<RestaurantTableDTO> createdTable = restaurantTableService.createTable(restaurantTableDTO);

        assertTrue(createdTable.isPresent());
        RestaurantTableDTO actualTableDTO = createdTable.get();

        assertEquals(restaurantTableDTO.getNumber(), actualTableDTO.getNumber());
        assertEquals(restaurantTableDTO.getSeats(), actualTableDTO.getSeats());

        Mockito.verify(mockRepository, Mockito.times(1)).save(expectedTable);
        Mockito.verifyNoMoreInteractions(mockRepository);
    }

    @Test
    public void testChangeTableSuccess() {
        int tableNumber = 1;
        int newSeats = 6;
        RestaurantTableDTO originalDto = new RestaurantTableDTO(tableNumber, 4);
        RestaurantTableDTO updatedDto = new RestaurantTableDTO(tableNumber, newSeats);

        RestaurantTable foundTable = new RestaurantTable(tableNumber, originalDto.getSeats());
        RestaurantTable savedTable = new RestaurantTable(tableNumber, newSeats);

        Mockito.when(mockRepository.findByNumber(tableNumber)).thenReturn(Optional.of(foundTable));
        Mockito.when(mockRepository.save(foundTable)).thenReturn(savedTable);

        RestaurantTableDTO resultDto = restaurantTableService.changeTable(originalDto);

        assertEquals(updatedDto, resultDto);

        Mockito.verify(mockRepository, Mockito.times(1)).findByNumber(tableNumber);
        Mockito.verify(mockRepository, Mockito.times(1)).save(foundTable);
    }

    @Test
    public void testChangeTableNotFound() {
        int tableNumber = 1;
        RestaurantTableDTO dto = new RestaurantTableDTO(tableNumber, 4);

        Mockito.when(mockRepository.findByNumber(tableNumber)).thenReturn(Optional.empty());

        assertThrows(TableNotFoundException.class, () -> restaurantTableService.changeTable(dto));

        Mockito.verify(mockRepository, Mockito.times(1)).findByNumber(tableNumber);
        Mockito.verify(mockRepository, Mockito.times(0)).save(any());
    }

    @Test
    public void testChangeTableSaveError() {
        int tableNumber = 1;
        RestaurantTableDTO dto = new RestaurantTableDTO(tableNumber, 4);

        RestaurantTable foundTable = new RestaurantTable(tableNumber, dto.getSeats());

        Mockito.when(mockRepository.findByNumber(tableNumber)).thenReturn(Optional.of(foundTable));
        Mockito.when(mockRepository.save(foundTable)).thenThrow(new RuntimeException("Simulated error"));

        assertThrows(RuntimeException.class, () -> restaurantTableService.changeTable(dto));

        // Перевірка викликів методу
        Mockito.verify(mockRepository, Mockito.times(1)).findByNumber(tableNumber);
        Mockito.verify(mockRepository, Mockito.times(1)).save(foundTable);
    }

    @Test
    public void testDeleteTableSuccess() {
        int tableId = 1;
        Mockito.when(mockRepository.existsById(tableId)).thenReturn(true);

        restaurantTableService.deleteTable(tableId);

        Mockito.verify(mockRepository, Mockito.times(1)).deleteById(tableId);
    }

    @Test
    public void testDeleteTableNotFound() {
        int tableId = 2;
        Mockito.when(mockRepository.existsById(tableId)).thenReturn(false);

        assertThrows(TableNotFoundException.class, () -> restaurantTableService.deleteTable(tableId));
    }

}
