package com.snort.inteli.cartapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class StockProductResponse {
	private Long id;
	private String title;
	private double price;
	private int stock;
	private double total;
	private double discountPercentage;
	private double discountedPrice;
}
