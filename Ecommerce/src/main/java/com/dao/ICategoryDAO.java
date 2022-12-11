package com.dao;

import com.entity.Category;
import java.util.List;

import org.springframework.stereotype.Repository;


public interface ICategoryDAO 
{
	boolean addCategory(Category category);
	Category getCategory(int categoryId);
	List<Category> getAllCategory();
	boolean deleteCategory(Category category);
	boolean updateCategory(Category category);
	
	/*additional changes by me*/
	Category getCategoryByName(String categoryName);	
}
