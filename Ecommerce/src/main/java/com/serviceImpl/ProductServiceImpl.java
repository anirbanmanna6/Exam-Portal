package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.IProductDAO;
import com.entity.Product;
import com.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDAO productDAO;
	
	@Override
	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		return productDAO.addProduct(product);
	}

	@Override
	public Product getProduct(int productId) {
		// TODO Auto-generated method stub
		return productDAO.getProduct(productId);
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productDAO.getAllProduct();
	}

	@Override
	public boolean deleteProduct(Product product) {
		// TODO Auto-generated method stub
		try {
		return productDAO.deleteProduct(product);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productDAO.updateProduct(product);
	}

}
