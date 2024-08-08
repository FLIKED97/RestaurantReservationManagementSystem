package com.example.RestaurantSystem.controller;

import com.example.RestaurantSystem.dto.PersonDTO;
import com.example.RestaurantSystem.services.PersonService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final ModelMapper modelMapper;
    private final PersonService personService;

    @GetMapping("/admin/show")
    public List<PersonDTO> getAllPerson(){
        return personService.getAllPerson();
    }

}
