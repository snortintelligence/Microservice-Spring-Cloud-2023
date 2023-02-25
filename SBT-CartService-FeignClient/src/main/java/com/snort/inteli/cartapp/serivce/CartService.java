package com.snort.inteli.cartapp.serivce;

import java.util.List;

import com.snort.inteli.cartapp.dto.ProductRequest;
import com.snort.inteli.cartapp.model.Cart;

public interface CartService {
	
	List<Cart> getCarts();

	Cart getCartById(Long id);

	Cart deleteCartById(Long id);

	Cart updateCart(Cart cart);
	
	Cart deleteCartProducts(Long cartId, List<Long> prodIds);
	
	Cart createCarts(List<ProductRequest> products);
}
