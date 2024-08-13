package com.example.RestaurantSystem.util;

import com.example.RestaurantSystem.models.Person;
import com.example.RestaurantSystem.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class PersonValidator implements Validator {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        // Перевірка наявності користувача з таким самим email
        try {
            personDetailsService.loadUserByUsername(person.getEmail());
            // Якщо користувач знайдений, кидаємо помилку
            errors.rejectValue("email", "", "A person with this email already exists");
        } catch (UsernameNotFoundException ignored) {
            // все ок, користувач не знайдений, нічого не робимо
        }

        // Додаткова перевірка: ім'я не повинно містити цифр
        if (person.getFirstName().matches(".*\\d.*")) {
            errors.rejectValue("FirstName", "", "FirstName should not contain numbers");
        }
        // Додаткова перевірка: ім'я не повинно містити цифр
        if (person.getLastName().matches(".*\\d.*")) {
            errors.rejectValue("LastName", "", "LastName should not contain numbers");
        }
    }

}