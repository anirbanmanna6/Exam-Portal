package com.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ordder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 int orderId,
int CartId
double totalAmount
String username
Date orderDate
String shippingAddress
String Trans
	 
	 */
	
	@Id
	@GeneratedValue
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="order_amount")
	private double orderAmount;
	
	@Column(name="order_date_time")
	private LocalDateTime orderDateTime = LocalDateTime.now();
	
	@Column(name="delivery_address")
	private String deliveryAddress;
	
	/*@Column(name="order_confirmed")
	private char orderConfirmed;
	
	@Column(name="order_delivered")
	private char orderDelivered;
	
	@Column(name="order_delivered")
	private char orderDelivered;
	*/
	
	//CON, CAN, DEL
	@Column(name="order_status")
	private String orderStatus;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "ordder")
	private List<Cart> cart;
	

	public Ordder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	


}
