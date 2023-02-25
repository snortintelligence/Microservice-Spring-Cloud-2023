package com.snort.inteli.cartapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponse {
	private Long id;
	private String title;
	private double price;
	private double total;
	private double discountPercentage;
	private double discountedPrice;
}
