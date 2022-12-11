package com.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Supplier implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="supplier_id")
	private int supplierId;
	@Column(name="supplier_name")
	private String supplierName;
	@Column(name="supplier_address")
	private String supplierAddress;
	
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "supplier")
	private Product product;
	
	public Supplier() 
	{}

	public Supplier(String supplierName, String supplierAddress) {
		super();
		this.supplierName = supplierName;
		this.supplierAddress = supplierAddress;
	}
	

	public Supplier(String supplierName, String supplierAddress, Product product) {
		super();
		this.supplierName = supplierName;
		this.supplierAddress = supplierAddress;
		this.product = product;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", supplierAddress="
				+ supplierAddress + "]";
	}
	
}
