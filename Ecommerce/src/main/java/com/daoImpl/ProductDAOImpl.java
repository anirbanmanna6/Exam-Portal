package com.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.dao.IProductDAO;
import com.entity.Product;

@Repository
@Transactional
public class ProductDAOImpl implements IProductDAO {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public boolean addProduct(Product product) {
		try
		{
		int productId = (Integer) hibernateTemplate.save(product);
		if (productId != 0)
			return true;
		return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Product getProduct(int productId) {
		Product fetchedProduct = null;
		try
		{
			fetchedProduct = hibernateTemplate.get(Product.class, productId);
			return fetchedProduct;
		}
		catch (Exception e) {
			e.printStackTrace();
			return fetchedProduct;
		}
		
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		try {
		return hibernateTemplate.loadAll(Product.class);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean deleteProduct(Product product) {
		// TODO Auto-generated method stub
		try {
			hibernateTemplate.delete(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		try {
			hibernateTemplate.update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
