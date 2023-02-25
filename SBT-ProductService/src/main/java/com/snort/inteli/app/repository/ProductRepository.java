package com.snort.inteli.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snort.inteli.app.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	//title
	List<Product> findByTitleIn(List<String> titles);
}
