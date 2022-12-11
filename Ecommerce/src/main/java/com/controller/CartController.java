package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.ICartDAO;
import com.dao.IProductDAO;
import com.dao.IUserDAO;
import com.entity.Cart;
import com.entity.Product;
import com.entity.User;
import com.service.ICartService;
import com.service.IProductService;
import com.service.IUserService;

@Controller
public class CartController 
{
	@Autowired
	IProductService productService;
	
	@Autowired
	ICartService cartService;
	
	@Autowired
	IUserService userService;
	
	List<Cart> cartList = new ArrayList<Cart>();
	
	//cart page show when we click CART button
	@RequestMapping(value="/cart")
	public String cartHandler(HttpSession session, Model m)
	{
		cartList = this.cartListHandler(session);
		System.out.println("CartController - cartHandler() "+cartList.size());
		double grandTotal = 0;	
		for(Cart cartItem : cartList)
		{
			if(cartItem!=null)
				grandTotal = grandTotal + (cartItem.getProduct().getProductPrice() * cartItem.getBuyQuantity() );
		}		
		m.addAttribute("cartList", cartList);
		m.addAttribute("grandTotal",grandTotal);
		return "cart";
		
	}
	
	
	//Fetches the List of All cart Items using email
	public List<Cart> cartListHandler(HttpSession session)
	{		
		cartList = cartService.getAllCart((String)session.getAttribute("userEmail"));
		return cartList;
	}
	
	//When we add new item to the cart
	@RequestMapping(value="/addToCart/{productId}")
	public String addtoCartHandler(HttpSession session, Model m, @PathVariable("productId") int productId, @RequestParam("buyQuantity") int buyQuantity)
	{
		String userEmail =  (String)session.getAttribute("userEmail"); //VVI
		System.out.println(userEmail);
		Product product = productService.getProduct(productId);
				
		cartList = this.cartListHandler(session);
		
		
		boolean productPresentInCart = false;
		
		// update the quantity if the product already present in Cart 
			for(Cart cartItem : cartList)
			{
				if(cartItem.getProduct().getProductId()==productId)
				{
					cartItem.setBuyQuantity(buyQuantity + cartItem.getBuyQuantity()); //old + new quantity
					cartService.updateCart(cartItem);
					productPresentInCart = true;
					break;
				}
			}


		if(!productPresentInCart)
		{
		
			Cart cart = new Cart();
			cart.setCartPaymentStatus("NA");
			cart.setProduct(product);
			cart.setBuyQuantity(buyQuantity);
			cart.setUser(userService.getUser(userEmail)); //VVI
		
			//saving the cart object
			cartService.addCart(cart);
		
			//update the cartList
			//cartList.add(cart);
			
		}
		cartList = this.cartListHandler(session);
		double grandTotal = 0;	
		for(Cart cartItem : cartList)
		{
			grandTotal = grandTotal + (cartItem.getProduct().getProductPrice() * cartItem.getBuyQuantity() );
		}
		m.addAttribute("cartList", cartList);
		m.addAttribute("grandTotal",grandTotal);
		
		return "cart";
	}
	

	@RequestMapping(value="/updateCartQuantity/{cartId}", method=RequestMethod.POST)
	public String updateCartQuantityHandler(Model m, @RequestParam("newQuantity") int newQuantity,
			@PathVariable("cartId") int cartId, HttpSession session
			)
	{
		System.out.println("newQuantity "+newQuantity);
		Cart cart = cartService.getCart(cartId);
		cart.setBuyQuantity(newQuantity);
		cartService.updateCart(cart); //update
			
		cartList = this.cartListHandler(session);
		double grandTotal = 0;	
		for(Cart cartItem : cartList)
		{
			grandTotal = grandTotal + (cartItem.getProduct().getProductPrice() * cartItem.getBuyQuantity() );
		}	
		m.addAttribute("cartList", cartList);
		m.addAttribute("grandTotal",grandTotal);
		return "cart";
	}
	
	@RequestMapping(value="/deleteCartItem/{cartId}", method = RequestMethod.POST )
	public String deleteCartItemHandler(Model m, @PathVariable("cartId") int cartId, HttpSession session)
	{
		cartService.deleteCart(cartService.getCart(cartId)); //delete
		
		cartList = this.cartListHandler(session);
		double grandTotal = 0;	
		for(Cart cartItem : cartList)
		{
			grandTotal = grandTotal + (cartItem.getProduct().getProductPrice() * cartItem.getBuyQuantity() );
		}	
		m.addAttribute("cartList", cartList);
		m.addAttribute("grandTotal",grandTotal);
		return "cart";
	}
	
	@RequestMapping(value="/checkOut")
	public String checkOutHandler(Model m, HttpSession session)
	{
		cartList = this.cartListHandler(session);
		double grandTotal = 0;	
		for(Cart cartItem : cartList)
		{
			grandTotal = grandTotal + (cartItem.getProduct().getProductPrice() * cartItem.getBuyQuantity());
		}
		User user = userService.getUser((String)session.getAttribute("userEmail")); 
		String address = user.getUserAddress();
		m.addAttribute("address",address);
		m.addAttribute("cartList", cartList);
		m.addAttribute("grandTotal",grandTotal);
		return "checkOut";
	}
	
	
}
