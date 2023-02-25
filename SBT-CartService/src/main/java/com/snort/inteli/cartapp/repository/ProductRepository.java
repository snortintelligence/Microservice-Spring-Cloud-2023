package com.snort.inteli.cartapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snort.inteli.cartapp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
