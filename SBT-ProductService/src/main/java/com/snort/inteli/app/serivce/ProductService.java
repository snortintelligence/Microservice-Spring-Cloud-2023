package com.snort.inteli.app.serivce;

import java.util.List;

import com.snort.inteli.app.model.Product;

public interface ProductService {
	List<Product> getProducts();

	Product getProductById(Long id);

	Product deleteProductById(Long id);

	Product updateProduct(Product products);

	List<Product> createProducts(List<Product> products);

	List<Product> findByTitleIn(List<String> titles);
}
