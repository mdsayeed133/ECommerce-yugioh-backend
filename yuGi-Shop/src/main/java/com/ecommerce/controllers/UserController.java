package com.ecommerce.controllers;

import com.ecommerce.DTOs.LoginRequest;
import com.ecommerce.DTOs.RegisterRequest;
import com.ecommerce.model.User;
import com.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Logins User
     *
     * @param loginRequest
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest){
        User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return  ResponseEntity.ok(user);
    }
    /**
     * Adds new user to database
     *
     * @param registerRequest
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest){
        User user= userService.register(registerRequest);
        return ResponseEntity.ok(user);
    }
}
