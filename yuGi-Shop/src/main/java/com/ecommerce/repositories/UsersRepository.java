package com.ecommerce.repositories;

import com.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameIgnoreCaseAndPassword(String username, String password);




}