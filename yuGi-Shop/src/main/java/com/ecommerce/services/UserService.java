package com.ecommerce.services;

import com.ecommerce.DTOs.RegisterRequest;
import com.ecommerce.exceptions.LoginException;
import com.ecommerce.model.User;
import com.ecommerce.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User login(String username, String password) throws LoginException {
        Optional<User> user= usersRepository.findByUsernameIgnoreCaseAndPassword(username,password);
        if(!user.isPresent()) throw new LoginException();
        return user.get();
    }

    public User register(RegisterRequest registerRequest){
        User newUser= new User(registerRequest.getUsername(),registerRequest.getPassword(),registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getCountry());
        return usersRepository.save(newUser);
    }


}
