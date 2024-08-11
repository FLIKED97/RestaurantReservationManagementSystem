package com.example.RestaurantSystem.controller;

import com.example.RestaurantSystem.dto.AuthenticationDTO;
import com.example.RestaurantSystem.dto.PersonDTO;
import com.example.RestaurantSystem.models.Person;
import com.example.RestaurantSystem.security.JWTUtil;
import com.example.RestaurantSystem.services.RegistrationService;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final RegistrationService registrationService;

    private final JWTUtil jwtUtil;

    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                                   BindingResult bindingResult) {
        Person person = convertToPerson(personDTO);

        //personValidator.validate(person, bindingResult); TODO

        if (bindingResult.hasErrors()) {
            return Map.of("message", "Помилка!");
        }

        registrationService.register(person);
        // Отримайте роль користувача після реєстрації
        String role = person.getRole();
        String token = jwtUtil.generateToken(person.getEmail(), List.of(role));
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        // Отримайте ролі користувача з вашого сервісу
        List<String> roles = registrationService.getUserRoles(authenticationDTO.getEmail());
        String token = jwtUtil.generateToken(authenticationDTO.getEmail(), roles);
        return Map.of("jwt-token", token);
    }


    public Person convertToPerson(PersonDTO personDTO) {
        return this.modelMapper.map(personDTO, Person.class);
    }
}
