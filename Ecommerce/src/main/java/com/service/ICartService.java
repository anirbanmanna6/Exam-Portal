package com.service;

import java.util.List;

import com.entity.Cart;

public interface ICartService {
	boolean addCart(Cart cart);
	Cart getCart(int cartId);	
	boolean deleteCart(Cart cart);
	boolean updateCart(Cart cart);
	
	/* Fetching Card Details with Email*/
	Cart getCart(String email);
	List<Cart> getAllCart(String email);
	
	boolean updateOrderId(String email, int ordderId);
	List<Cart> getCartUsingOrdderId(int ordderId);
}
