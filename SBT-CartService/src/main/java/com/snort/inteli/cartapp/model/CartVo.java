package com.snort.inteli.cartapp.model;

import java.util.ArrayList;

public class CartVo {
	public ArrayList<Cart> carts;
	public int total;
	public int skip;
	public int limit;

	public ArrayList<Cart> getCarts() {
		return carts;
	}

	public void setCarts(ArrayList<Cart> carts) {
		this.carts = carts;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "CartVo [carts=" + carts + ", total=" + total + ", skip=" + skip + ", limit=" + limit + "]";
	}

}
