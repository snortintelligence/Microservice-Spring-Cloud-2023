package com.snort.inteli.cartapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snort.inteli.cartapp.dto.ProductRequest;
import com.snort.inteli.cartapp.model.Cart;
import com.snort.inteli.cartapp.serivce.CartService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/carts")
@Slf4j
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping
	public List<Cart> getCarts() {
		return cartService.getCarts();
	}

	@GetMapping("/{id}")
	public Cart getCartById(@PathVariable Long id) {
		return cartService.getCartById(id);
	}

	@DeleteMapping("/{id}")
	public Cart deleteCartById(@PathVariable Long id) {
		return cartService.deleteCartById(id);
	}

	@DeleteMapping("/products/delete")
	public Cart deleteCartProductsById(@RequestParam(value = "cartId") Long cartId,
			@RequestParam(value = "prodId") List<Long> prodId) {
		return cartService.deleteCartProducts(cartId, prodId);
	}

	@PutMapping
	public Cart updateCart(@RequestBody Cart cart) {
		return cartService.updateCart(cart);
	}

	@PostMapping("/add")
	public Cart addProductsInCart(@RequestBody List<ProductRequest> cartItems) {
		log.info("ProductRequest {}: ", cartItems);
		return cartService.createCarts(cartItems);
	}

}
