package com.snort.inteli.cartapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class CartSummaryReponse {
	private List<CartResponse> carts;
}
