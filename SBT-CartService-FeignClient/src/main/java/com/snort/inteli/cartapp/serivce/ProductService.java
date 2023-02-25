package com.snort.inteli.cartapp.serivce;

import java.util.List;

import com.snort.inteli.cartapp.dto.ProductRequest;
import com.snort.inteli.cartapp.dto.StockProductResponse;
import com.snort.inteli.cartapp.model.Product;

public interface ProductService {
	
	List<Product> getProducts();
	
	List<StockProductResponse> getProductsInStock();
	
	List<StockProductResponse> getProductInStockByProductTitle(List<ProductRequest> products);
	
	StockProductResponse getProductInStockByProductId(Long id);

	Product getProductById(Long id);

	Product deleteProductById(Long id);

	Product updateProduct(Product products);

	List<Product> createProducts(List<Product> products);
}
