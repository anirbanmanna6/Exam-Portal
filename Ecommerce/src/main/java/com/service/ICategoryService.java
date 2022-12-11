package com.service;

import java.util.List;

import com.entity.Category;

public interface ICategoryService 
{	
	boolean addCategory(Category category);
	Category getCategory(int categoryId);
	List<Category> getAllCategory();
	boolean deleteCategory(Category category);
	boolean updateCategory(Category category);	
	
	/*additional changes by me*/
	Category getCategoryByName(String categoryName);
}
