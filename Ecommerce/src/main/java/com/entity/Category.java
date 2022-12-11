package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="category_id")
	private int categoryId;
	@Column(name="category_name")
	private String categoryName;
	@Column(name="category_desc")
	private String categoryDesc;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Product> product;
	
	public Category() {
	}
	
	public Category(String categoryName, String categoryDesc) {
		super();
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
	}

	public Category(String categoryName, String categoryDesc, List<Product> product) {
		super();
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.product = product;
	}

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	
	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDesc="
				+ categoryDesc + "]";
	}
	
}
