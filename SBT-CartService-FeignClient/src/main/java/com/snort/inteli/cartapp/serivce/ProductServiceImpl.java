package com.snort.inteli.cartapp.serivce;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.snort.inteli.cartapp.client.ClientProductService;
import com.snort.inteli.cartapp.dto.ProductRequest;
import com.snort.inteli.cartapp.dto.StockProductResponse;
import com.snort.inteli.cartapp.model.Product;
import com.snort.inteli.cartapp.repository.ProductRepository;
import com.snort.inteli.cartapp.utils.CartUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private ClientProductService clientProductService;

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
		List<StockProductResponse> productsDtos = clientProductService.getProductsInStock();
		return productsDtos;
	}

	@Override
	public List<StockProductResponse> getProductInStockByProductTitle(List<ProductRequest> products) {
		Set<String> titlesParam = products.stream().map(title -> title.getTitle()).collect(Collectors.toSet());
		String titles = CartUtils.convertListToPathParams(titlesParam);
		List<StockProductResponse> productsDtos = clientProductService.getProductInStockByProductTitle(titles);
		return productsDtos;
	}

	@Override
	public StockProductResponse getProductInStockByProductId(Long id) {
		StockProductResponse productsDtos = clientProductService.getProductInStockByProductId(id);
		return productsDtos;
	}

}
