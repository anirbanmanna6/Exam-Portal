package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ICategoryDAO;
import com.entity.Category;
import com.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	ICategoryDAO categoryDAO;
	
	
	@Override
	public boolean addCategory(Category category) {
		return categoryDAO.addCategory(category);
	}

	@Override
	public Category getCategory(int categoryId) {
		return categoryDAO.getCategory(categoryId);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryDAO.getAllCategory();
	}

	@Override
	public boolean deleteCategory(Category category) {
		return categoryDAO.deleteCategory(category);
	}

	@Override
	public boolean updateCategory(Category category) {
		return categoryDAO.updateCategory(category);
	}

	/*additional changes by me*/
	@Override
	public Category getCategoryByName(String categoryName) {
		// TODO Auto-generated method stub
		return categoryDAO.getCategoryByName(categoryName);
	}

}
