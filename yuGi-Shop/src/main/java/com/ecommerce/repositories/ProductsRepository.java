package com.ecommerce.repositories;

import com.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
}