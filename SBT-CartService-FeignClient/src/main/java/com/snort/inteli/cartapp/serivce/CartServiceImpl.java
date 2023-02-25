package com.snort.inteli.cartapp.serivce;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snort.inteli.cartapp.dto.ProductRequest;
import com.snort.inteli.cartapp.dto.StockProductResponse;
import com.snort.inteli.cartapp.model.Cart;
import com.snort.inteli.cartapp.repository.CartRepository;
import com.snort.inteli.cartapp.utils.CartUtils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductService productService;

	@Override
	public List<Cart> getCarts() {
		List<Cart> carts = cartRepository.findAll();
		System.out.println(carts);
		return carts;
	}

	@Override
	public Cart getCartById(Long id) {
		return cartRepository.findById(id).get();
	}

	@Override
	public Cart deleteCartById(Long id) {
		Cart cart = cartRepository.findById(id).get();
		cartRepository.delete(cart);
		return cart;
	}

	@Override
	public Cart updateCart(Cart cart) {
		return cartRepository.save(cart);
	}

	@Override
	public Cart createCarts(List<ProductRequest> products) {

		// fetching stock items from Product Microservice
		List<StockProductResponse> stockItems = productService.getProductInStockByProductTitle(products);
		Map<String, Object> map = CartUtils.getCartAndProducts(stockItems, products);

		Cart cartItem = new ObjectMapper().convertValue(map.get("cart"), new TypeReference<Cart>() {
		});

		log.info("Cart Saved {}: ", cartItem);
		cartItem = cartRepository.save(cartItem);
		return cartItem;
	}

	@Override
	public Cart deleteCartProducts(Long cartId, List<Long> prodIds) {
		Optional<Cart> cart = cartRepository.findById(cartId);
		if (!cart.isPresent()) {
			throw new RuntimeException("No cart found!");
		}
		cartRepository.deleteCartProducts(cartId, prodIds);
		return cartRepository.findById(cartId).get();
	}
}
