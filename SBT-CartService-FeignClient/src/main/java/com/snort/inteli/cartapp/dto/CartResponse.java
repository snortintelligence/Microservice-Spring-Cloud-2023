package com.snort.inteli.cartapp.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartResponse {

	private Long cartId;
	private List<ProductResponse> products;
	private double totalProductAmount;
	private double discountedTotalAmount;
	private int totalProducts;
	private int totalQuantity;

}
