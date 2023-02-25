package com.snort.inteli.cartapp.serivce;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.snort.inteli.cartapp.dto.ProductRequest;
import com.snort.inteli.cartapp.dto.StockProductResponse;
import com.snort.inteli.cartapp.model.Product;
import com.snort.inteli.cartapp.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	RestTemplate restTemplate;

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
		return null;
	}

	@Override
	public List<StockProductResponse> getProductsInStock() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<StockProductResponse[]> productsEntity = restTemplate.exchange(
				"http://PRODUCTSMICROSERVICE1/products", HttpMethod.GET, entity, StockProductResponse[].class);
		StockProductResponse[] productsDtos = productsEntity.getBody();
		return Arrays.asList(productsDtos);
	}

	@Override
	public List<StockProductResponse> getProductInStockByProductTitle(List<ProductRequest> products) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String titlesParam = products.stream().map(title -> title.getTitle()).collect(Collectors.toSet()).toString();
		titlesParam = titlesParam.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(", ", ",");
		String queryApi = "http://PRODUCTSMICROSERVICE1/products/title/" + titlesParam;
		ResponseEntity<StockProductResponse[]> productsEntity = restTemplate.exchange(queryApi, HttpMethod.GET, entity,
				StockProductResponse[].class);
		StockProductResponse[] productsDtos = productsEntity.getBody();

		return Arrays.asList(productsDtos);
	}

	@Override
	public StockProductResponse getProductInStockByProductId(Long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		String queryApi = "http://PRODUCTSMICROSERVICE1/products/" + id;

		ResponseEntity<StockProductResponse> productsEntity = restTemplate.exchange(queryApi, HttpMethod.GET, entity,
				StockProductResponse.class);
		StockProductResponse productsDtos = productsEntity.getBody();
		return productsDtos;
	}

}
