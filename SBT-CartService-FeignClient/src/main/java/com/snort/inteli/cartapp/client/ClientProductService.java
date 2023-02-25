package com.snort.inteli.cartapp.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.snort.inteli.cartapp.dto.StockProductResponse;

@FeignClient(name="PRODUCTSMICROSERVICE1")
public interface ClientProductService {

	@GetMapping("/products")
	List<StockProductResponse> getProductsInStock();
	@GetMapping("products/titles/{titles}")
	List<StockProductResponse> getProductInStockByProductTitle(@PathVariable("titles")String titles);
	@GetMapping("/products/{id}")
	StockProductResponse getProductInStockByProductId(@PathVariable("id") Long id);
	 
}
