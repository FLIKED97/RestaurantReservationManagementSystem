package com.example.RestaurantSystem.services;

import com.example.RestaurantSystem.dto.PersonDTO;
import com.example.RestaurantSystem.models.Person;
import com.example.RestaurantSystem.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    public void blockUser(String email) {
        Optional<Person> personOptional = personRepository.findByEmail(email);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setAccountNonLocked(false);
            personRepository.save(person);
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    public void unblockUser(String email) {
        Optional<Person> personOptional = personRepository.findByEmail(email);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setAccountNonLocked(true);
            personRepository.save(person);
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    public void enableUser(String email) {
        Optional<Person> personOptional = personRepository.findByEmail(email);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setEnabled(true);
            personRepository.save(person);
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    public void disableUser(String email) {
        Optional<Person> personOptional = personRepository.findByEmail(email);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setEnabled(false);
            personRepository.save(person);
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    public void setAdmin(String email) {
        Optional<Person> personOptional = personRepository.findByEmail(email);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setRole("ROLE_ADMIN");
            personRepository.save(person);
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    public void setUser(String email) {
        Optional<Person> personOptional = personRepository.findByEmail(email);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setRole("ROLE_USER");
            personRepository.save(person);
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}
