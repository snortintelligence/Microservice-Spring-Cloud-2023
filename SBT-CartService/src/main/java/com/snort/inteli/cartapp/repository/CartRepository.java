package com.snort.inteli.cartapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.snort.inteli.cartapp.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	
	@Modifying
	@Query(value ="delete from tbl_cart_products where fk_cart_id=?1 AND prod_id IN (?2)", nativeQuery = true)
	void deleteCartProducts(@Param("cartId") Long cartId,@Param("prodIds") List<Long> prodIds);
}
