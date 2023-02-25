package com.snort.inteli.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snort.inteli.app.model.Product;
import com.snort.inteli.app.serivce.ProductService;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getProducts() {
		return productService.getProducts();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@DeleteMapping("/{id}")
	public Product deleteProductById(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return productService.deleteProductById(id);
	}

	@PutMapping
	public Product updateProduct(@RequestBody Product products) {
		return productService.updateProduct(products);
	}

	@PostMapping
	public List<Product> createProducts(@RequestBody List<Product> products) {
		return productService.createProducts(products);
	}

}
