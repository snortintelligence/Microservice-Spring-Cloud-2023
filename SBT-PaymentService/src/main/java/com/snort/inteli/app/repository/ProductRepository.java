package com.snort.inteli.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snort.inteli.app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
