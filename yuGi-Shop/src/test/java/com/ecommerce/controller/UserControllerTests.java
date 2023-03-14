package com.ecommerce.controller;


import com.ecommerce.DTOs.LoginRequest;
import com.ecommerce.DTOs.RegisterRequest;
import com.ecommerce.controllers.UserController;
import com.ecommerce.model.User;
import com.ecommerce.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService))
                .build();
    }

    @Test
    void loginReturnsUser() throws Exception {
        User user = new User(1, "johndoe", "password", "John", "Doe", null);
        LoginRequest loginRequest = new LoginRequest("johndoe", "password");
        when(userService.login("johndoe", "password")).thenReturn(user);
        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", equalTo("johndoe")))
                .andExpect(jsonPath("$.password", equalTo("password")))
                .andExpect(jsonPath("$.firstName", equalTo("John")))
                .andExpect(jsonPath("$.lastName", equalTo("Doe")));
    }

    @Test
    void registerReturnsUser() throws Exception {
        User user = new User(1, "johndoe", "password", "John", "Doe", null);
        RegisterRequest registerRequest = new RegisterRequest("johndoe", "password", "John", "Doe", 1);
        when(userService.register(any(RegisterRequest.class))).thenReturn(user);
        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username", equalTo("johndoe")))
                .andExpect(jsonPath("$.password", equalTo("password")))
                .andExpect(jsonPath("$.firstName", equalTo("John")))
                .andExpect(jsonPath("$.lastName", equalTo("Doe")));
    }

    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
