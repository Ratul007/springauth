package com.example.springauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springauth.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
 
}