package com.snort.inteli.cartapp.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.snort.inteli.cartapp.dto.ProductRequest;
import com.snort.inteli.cartapp.dto.StockProductResponse;
import com.snort.inteli.cartapp.model.Cart;
import com.snort.inteli.cartapp.model.Product;

public class CartUtils {
	
	public static Map<String, String> getCartSummary(Set<Product> products) {
		Map<String, String> summary = new HashMap<>();
		double totalCartAmount = 0;
		int totalCartItems = products.size();
		int totalProductQuantities = 0;
		double discountedTotal = 0;
		for (Product product : products) {
			totalCartAmount = totalCartAmount + product.getTotal();
			totalProductQuantities = totalProductQuantities + product.getQuantity();
			discountedTotal = discountedTotal + product.getDiscountedPrice();
		}
		summary.put("total", String.valueOf(totalCartAmount));
		summary.put("discountedTotal", String.valueOf(discountedTotal));
		summary.put("totalProducts", String.valueOf(totalCartItems));
		summary.put("totalQuantity", String.valueOf(totalProductQuantities));
		return summary;
	}

	public static Map<String, Object> getCartAndProducts(List<StockProductResponse> stockItems,
			List<ProductRequest> products) {
		Map<String, Object> cartAndProducts = new HashMap<>();

		// set Items
		Set<Product> productList = new HashSet<>();

		stockItems.stream().map(stockItem -> {

			ProductRequest productRequest = products.stream().filter(p -> p.getTitle().equals(stockItem.getTitle()))
					.findFirst().get();
			int quantity = productRequest.getQuantity();
			double totaAmount = stockItem.getPrice() * quantity;
			double discountPrice = calculateDiscountedPrice(totaAmount, stockItem.getDiscountPercentage());

			Product productEntity = Product.builder().title(stockItem.getTitle()).price(stockItem.getPrice())
					.quantity(quantity).total(totaAmount).discountedPrice(discountPrice)
					.discountPercentage(stockItem.getDiscountPercentage()).build();
			productList.add(productEntity);

			return null;
		}).collect(Collectors.toList());

		Map<String, String> cartSummary = CartUtils.getCartSummary(productList);

		Cart cartEntity = new Cart();
		cartEntity.setCartTitle("Cart"+(new Random().nextLong(9999)));
		cartEntity.setTotal(Double.valueOf(cartSummary.get("total")));
		cartEntity.setProducts(productList);
		cartEntity.setTotalProducts(Integer.valueOf(cartSummary.get("totalProducts")));
		cartEntity.setTotalQuantity(Integer.valueOf(cartSummary.get("totalQuantity")));
		cartEntity.setDiscountedTotal(Double.valueOf(cartSummary.get("discountedTotal")));

		cartAndProducts.put("cart", cartEntity);
		cartAndProducts.put("products", productList);

		return cartAndProducts;
	}

	private static double calculateDiscountedPrice(double totalAmount, double discountPercentage) {
		return (totalAmount - (totalAmount * discountPercentage / 100));
	}
	
	public static String convertListToPathParams(Set<String> titlesParam) {
		String titles = titlesParam.toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(", ", ",");
		return titles;
	}
}
