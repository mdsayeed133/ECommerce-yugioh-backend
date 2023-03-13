package com.ecommerce.repositories;

import com.ecommerce.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardsRepository extends JpaRepository<Card, Integer> {
}