package com.dao;

import java.util.List;

import com.entity.Product;

public interface IProductDAO {
	boolean addProduct(Product product);
	Product getProduct(int productId);
	List<Product> getAllProduct();
	boolean deleteProduct(Product product);
	boolean updateProduct(Product product);	
}
