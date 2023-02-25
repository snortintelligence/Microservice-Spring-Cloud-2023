package com.snort.inteli.cartapp.controller;

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

import com.snort.inteli.cartapp.dto.ProductRequest;
import com.snort.inteli.cartapp.dto.StockProductResponse;
import com.snort.inteli.cartapp.model.Product;
import com.snort.inteli.cartapp.serivce.ProductService;

@RestController
@RequestMapping("/carts/products")
public class CartProductsController {

	@Autowired
	private ProductService productService;

	@GetMapping("/stock")
	public List<StockProductResponse> getProducts() {
		return productService.getProductsInStock();
	}

	@GetMapping("/title")
	public List<StockProductResponse> getProductByTitle(@RequestBody List<ProductRequest> products) {
		return productService.getProductInStockByProductTitle(products);
	}
	
	@GetMapping("/{id}")
	public StockProductResponse getProductById(@PathVariable Long id) {
		return productService.getProductInStockByProductId(id);
	}

	@DeleteMapping("/{id}")
	public Product deleteProductById(@PathVariable Long id) {
		return productService.deleteProductById(id);
	}

	@PutMapping
	public Product updateProduct(@RequestBody Product products) {
		return productService.updateProduct(products);
	}

	@PostMapping("/add")
	public List<Product> createProducts(@RequestBody List<Product> products) {
		System.out.println(products);
		return productService.createProducts(products);
	}

}
