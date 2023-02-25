package com.snort.inteli.app.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.snort.inteli.app.model.Product;
import com.snort.inteli.app.model.ProductsVo;
import com.snort.inteli.app.serivce.ProductService;

@RestController
@RequestMapping("/dummyapi")
public class DummyApiController {

	@Autowired
	private ProductService productService;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/products")
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
}
