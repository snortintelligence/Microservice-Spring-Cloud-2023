package com.snort.inteli.app.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.snort.inteli.app.model.Product;
import com.snort.inteli.app.model.ProductsVo;
import com.snort.inteli.app.serivce.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/dummyapi")
	public ProductsVo findAllProducts() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ProductsVo productsVo = restTemplate
				.exchange("https://dummyjson.com/products", HttpMethod.GET, entity, ProductsVo.class).getBody();

		List<Product> products = productsVo.getProducts();
		products = productService.createProducts(products);
		System.out.println("Total products saved successfully in db = " + products.size());
		return productsVo;
	}

	@GetMapping
	public List<Product> getProducts() {
		return productService.getProducts();
	}
	
	@GetMapping("titles/{titles}")
	public List<Product> findByTitleIn(@PathVariable String titles){
		List<Product> products = productService.findByTitleIn(Arrays.asList(titles.split(",")));
		return products;
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@DeleteMapping("/{id}")
	public Product deleteProductById(@PathVariable Long id) {
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
