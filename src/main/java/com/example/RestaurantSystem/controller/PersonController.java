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
    @PutMapping("/admin/block_user/{email}")
    public void blockUser(@PathVariable String email){
        personService.blockUser(email);
    }
    @PutMapping("/admin/unblock_user/{email}")
    public void unBlockUser(@PathVariable String email){
        personService.unblockUser(email);
    }
    @PutMapping("/admin/set_user/{email}")
    public void setUser(@PathVariable String email){
        personService.setUser(email);
    }
    @PutMapping("/admin/set_admin/{email}")
    public void setAdmin(@PathVariable String email){
        personService.setAdmin(email);
    }
}
