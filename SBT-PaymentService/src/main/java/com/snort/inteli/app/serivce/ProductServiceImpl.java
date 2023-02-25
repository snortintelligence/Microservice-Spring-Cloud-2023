package com.snort.inteli.app.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snort.inteli.app.model.Product;
import com.snort.inteli.app.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product deleteProductById(Long id) {
		Product products = productRepository.findById(id).get();
		productRepository.delete(products);
		return products;
	}

	@Override
	public Product updateProduct(Product products) {
		return productRepository.save(products);
	}

	@Override
	public List<Product> createProducts(List<Product> products) {
		return productRepository.saveAll(products);
	}

}
