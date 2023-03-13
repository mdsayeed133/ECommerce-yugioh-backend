package com.ecommerce.repositories;

import com.ecommerce.model.CardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardTypeRepository extends JpaRepository<CardType, Integer> {
}