package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ICartDAO;
import com.entity.Cart;
import com.service.ICartService;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	private ICartDAO cartDAO;
	
	@Override
	public boolean addCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartDAO.addCart(cart);
	}

	@Override
	public Cart getCart(int cartId) {
		// TODO Auto-generated method stub
		return cartDAO.getCart(cartId);
	}

	@Override
	public boolean deleteCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartDAO.deleteCart(cart);
	}

	@Override
	public boolean updateCart(Cart cart) {
		// TODO Auto-generated method stub
		return cartDAO.updateCart(cart);
	}

	@Override
	public Cart getCart(String email) {
		// TODO Auto-generated method stub
		return cartDAO.getCart(email);
	}

	@Override
	public List<Cart> getAllCart(String email) {
		// TODO Auto-generated method stub
		return cartDAO.getAllCart(email);
	}

	@Override
	public boolean updateOrderId(String email, int ordderId) {
		// TODO Auto-generated method stub
		return cartDAO.updateOrderId(email, ordderId);
	}

	@Override
	public List<Cart> getCartUsingOrdderId(int ordderId) {
		// TODO Auto-generated method stub
		return cartDAO.getCartUsingOrdderId(ordderId);
	}

}
