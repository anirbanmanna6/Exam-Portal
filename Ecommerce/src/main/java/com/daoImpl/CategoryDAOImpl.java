package com.daoImpl;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.dao.ICategoryDAO;
import com.entity.Category;


@Repository("categoryDAO") // if we call getBean then we can use this name
@Transactional
public class CategoryDAOImpl implements ICategoryDAO{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean addCategory(Category category) {
		
		int categoryId = (Integer) hibernateTemplate.save(category);
		if(categoryId!=0)
			return true;
		return false;
	}

	@Override
	public Category getCategory(int categoryId) {
		Category fetchedCategory = hibernateTemplate.get(Category.class, categoryId);
		return fetchedCategory;
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return hibernateTemplate.loadAll(Category.class);
	}

	@Override
	public boolean deleteCategory(Category category) {
		// TODO Auto-generated method stub
		try
		{
			hibernateTemplate.delete(category);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCategory(Category category) {
		// TODO Auto-generated method stub
		try
		{
			hibernateTemplate.update(category);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		 
	}

	/*additional changes by me*/
	@Override
	public Category getCategoryByName(String categoryName) {
		Category fetchedCategory = null;
		try
		{
			 //Object[] queryParam = {1,categoryName};
			//fetchedCategory = (Category) hibernateTemplate.find("from Category c where c.categoryName=?1",queryParam).get(0);
			 
			 String query = "from Category where categoryName=:x";
			 Session session = hibernateTemplate.getSessionFactory().openSession();
				Query q = session.createQuery(query);
				q.setString("x", categoryName);   // additional step as compared to JPQL
				
				//single -> uniqueResult & uniqueResultOptional
				//multiple -> list()
				fetchedCategory = (Category) q.uniqueResult();
			 session.close();
			 //fetchedCategory = (Category) hibernateTemplate.find("from Category c where c.categoryName=:categoryName").get(0);
			 return fetchedCategory;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return fetchedCategory;
	}
	
}
