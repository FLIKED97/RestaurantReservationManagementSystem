package com.example.RestaurantSystem.controller;

import com.example.RestaurantSystem.dto.AuthenticationDTO;
import com.example.RestaurantSystem.dto.PersonDTO;
import com.example.RestaurantSystem.models.Person;
import com.example.RestaurantSystem.security.JWTUtil;
import com.example.RestaurantSystem.services.RegistrationService;
import com.example.RestaurantSystem.util.PersonValidator;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final RegistrationService registrationService;

    private final JWTUtil jwtUtil;

    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;

    private final PersonValidator personValidator;

    @PostMapping("/registration")
    public ResponseEntity<Map<String, Object>> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                                                    BindingResult bindingResult) {
        Person person = convertToPerson(personDTO);
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return createErrorResponse(bindingResult);
        }

        registrationService.register(person);

        String token = jwtUtil.generateToken(person.getEmail(), List.of(person.getRole()));

        Map<String, Object> response = Map.of("jwt-token", token);

        return ResponseEntity.ok(response);
    }

    private ResponseEntity<Map<String, Object>> createErrorResponse(BindingResult bindingResult) {
        List<String> errorMessages = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        Map<String, Object> response = Map.of(
                "message", "Виникли помилки при реєстрації",
                "errors", errorMessages
        );

        return ResponseEntity.badRequest().body(response);
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
