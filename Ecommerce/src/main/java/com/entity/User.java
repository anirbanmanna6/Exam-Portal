package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="user_email")
	private String userEmail;
	
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="user_role")
	private String userRole;

	@Column(name="user_enabled")
	private boolean userEnabled;
	
	@Column(name="user_name")
	private String userName; // name of the user
	
	@Column(name="user_address")
	private String userAddress;
	
	@OneToMany(mappedBy = "user")
	private List<Cart> cart;
	
	@OneToMany(mappedBy = "user")
	private List<Ordder> ordder;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userEmail, String userPassword, String userRole, boolean userEnabled, String userName,
			String userAddress) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userEnabled = userEnabled;
		this.userName = userName;
		this.userAddress = userAddress;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public boolean isUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "User [userEmail=" + userEmail + ", userPassword=" + userPassword + ", userRole=" + userRole
				+ ", userEnabled=" + userEnabled + ", userName=" + userName + ", userAddress=" + userAddress + "]";
	}	
}
