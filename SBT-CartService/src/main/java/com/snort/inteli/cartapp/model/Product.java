package com.snort.inteli.cartapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tbl_cart_Products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "prod_id")
	public Long prodId;
	public String title;
	public double price;
	public int quantity;
	public double total;
	public double discountPercentage;
	public double discountedPrice;

}
