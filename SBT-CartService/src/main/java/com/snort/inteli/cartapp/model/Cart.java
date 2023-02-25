package com.snort.inteli.cartapp.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tbl_Cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cart_id")
	public Long cartId; 
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_cart_id", referencedColumnName = "cart_id")
	public Set<Product> products;
	public double total;
	public double discountedTotal;
	public int totalProducts;
	public int totalQuantity;
	@Column(unique = true)
	public String cartTitle;

}
