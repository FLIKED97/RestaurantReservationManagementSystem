package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.dto.PersonDTO;
import com.example.RestaurantSystem.models.Person;
import com.example.RestaurantSystem.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;


    private PersonDTO convertToPersonDTO(Person person){
        return modelMapper.map(person, PersonDTO.class);
    }
    private Person convertToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }

    public List<PersonDTO> getAllPerson() {
        return personRepository.findAll().stream().map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }
//    public PersonDTO addPerson(PersonDTO personDTO) {
//        Person person = convertToPerson(personDTO);
//        person.setCreatedAt(new Date());
//        Person savedPerson = personRepository.save(person);
//        return convertToPersonDTO(savedPerson);
//    }
}
