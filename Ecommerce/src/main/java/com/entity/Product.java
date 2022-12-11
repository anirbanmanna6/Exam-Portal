package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.entity.Cart;

@Entity
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//id, name, desc, price, quantity, categoryId, supplierId
	
	@Id
	@GeneratedValue
	@Column(name="product_id")
	private int productId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_desc")
	private String productDesc;
	
	@Column(name="product_price")
	private double productPrice;
	
	@Column(name="product_quantity")
	private int productQuantity; // stock quantity
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Supplier supplier;
	
	@OneToMany(mappedBy = "product")
	private List<Cart> cart;
	
	@Column(name="img_url")
	private String imgUrl;


	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String productName, String productDesc, double productPrice, int productQuantity, Category category,
			Supplier supplier) {
		super();
		this.productName = productName;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.category = category;
		this.supplier = supplier;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	
	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDesc=" + productDesc
				+ ", productPrice=" + productPrice + ", productQuantity=" + productQuantity + ", category=" + category
				+ ", supplier=" + supplier + ", imgUrl=" + imgUrl + "]";
	}

}
