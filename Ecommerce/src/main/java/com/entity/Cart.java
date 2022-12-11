package com.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Cart implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//id, productId, productQuantity -> int
	//productPrice  -> double
	//productName, userEmail(username), paymentStatus -> String
	
	@Id
	@GeneratedValue
	@Column(name="cart_id")
	private int cartId;
	
	@Column(name="cart_payment_status")
	private String cartPaymentStatus;
	
	@Column(name="buy_quantity")
	private int buyQuantity;
	
	@Column(name="buy_price")
	private double buyPrice;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Ordder ordder;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}	
	public Cart(String cartPaymentStatus, Product product, User user) {
		super();
		this.cartPaymentStatus = cartPaymentStatus;
		this.product = product;
		this.user = user;
	}
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getCartPaymentStatus() {
		return cartPaymentStatus;
	}

	public void setCartPaymentStatus(String cartPaymentStatus) {
		this.cartPaymentStatus = cartPaymentStatus;
	}

	
	
	public int getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(int buyQuantity) {
		this.buyQuantity = buyQuantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Cart(int cartId, String cartPaymentStatus, int buyQuantity) {
		super();
		this.cartId = cartId;
		this.cartPaymentStatus = cartPaymentStatus;
		this.buyQuantity = buyQuantity;
	}
	
}
