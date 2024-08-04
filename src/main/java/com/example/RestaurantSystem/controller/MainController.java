package com.example.RestaurantSystem.controller;

import com.example.RestaurantSystem.dto.PersonDTO;
import com.example.RestaurantSystem.services.PersonService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {
    private final ModelMapper modelMapper;
    private final PersonService personService;

    @GetMapping("/person/show")
    public List<PersonDTO> getAllPerson(){
        return personService.getAllPerson();
    }
    @PostMapping("/person/add")
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO) {
        return personService.addPerson(personDTO);
    }
}
