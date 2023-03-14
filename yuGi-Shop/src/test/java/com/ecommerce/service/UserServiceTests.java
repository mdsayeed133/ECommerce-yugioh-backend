package com.ecommerce.service;

import com.ecommerce.DTOs.RegisterRequest;
import com.ecommerce.exceptions.CountryException;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.model.Country;
import com.ecommerce.model.User;
import com.ecommerce.repositories.CountryRepository;
import com.ecommerce.repositories.UsersRepository;
import com.ecommerce.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTests {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void loginWithValidCredentialsReturnsUser() throws LoginException {
        User user = new User("johndoe", "password", "John", "Doe", new Country(0,"US"));
        when(usersRepository.findByUsernameIgnoreCaseAndPassword("johndoe", "password")).thenReturn(Optional.of(user));
        User result = userService.login("johndoe", "password");
        assertEquals(user, result);
    }

    @Test
    void loginWithInvalidCredentialsThrowsLoginException() throws LoginException {
        when(usersRepository.findByUsernameIgnoreCaseAndPassword("johndoe", "password")).thenReturn(Optional.empty());
        assertThrows(LoginException.class, () -> userService.login("johndoe", "password"));
    }

    @Test
    void registerWithValidCountryReturnsNewUser() throws CountryException {
        Country country = new Country(0,"US");
        when(countryRepository.findById(0)).thenReturn(Optional.of(country));
        RegisterRequest registerRequest = new RegisterRequest("johndoe", "password", "John", "Doe", 0);
        User expectedUser = new User("johndoe", "password", "John", "Doe", country);
        when(usersRepository.save(any(User.class))).thenReturn(expectedUser);
        User result = userService.register(registerRequest);
        assertEquals(expectedUser, result);
    }


    @Test
    void registerWithInvalidCountryThrowsCountryException() throws CountryException {
        when(countryRepository.findById(1)).thenReturn(Optional.empty());
        RegisterRequest registerRequest = new RegisterRequest("johndoe", "password", "John", "Doe", 1);
        assertThrows(CountryException.class, () -> userService.register(registerRequest));
    }


}
