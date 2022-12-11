package com.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.dao.IUserDAO;
import com.entity.Category;
import com.entity.User;

@Repository
@Transactional
public class UserDAOImpl implements IUserDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public boolean addUser(User user) {
		String createdUser = (String) hibernateTemplate.save(user);
		if (createdUser != null)
			return true;
		return false;
	}

	@Override
	public User getUser(int userId) {
		User fetchedUser = hibernateTemplate.get(User.class, userId);
		return fetchedUser;
	}
	
	@Override
	public User getUser(String email) {
		User fetchedUser = null;
		try
		{
			String query = "from User where userEmail=:x";
			 Session session = hibernateTemplate.getSessionFactory().openSession();
				Query q = session.createQuery(query);
				q.setString("x", email);   // additional step as compared to JPQL
				
				//single -> uniqueResult & uniqueResultOptional
				//multiple -> list()
				fetchedUser = (User) q.uniqueResult();
			 session.close();
			 //fetchedCategory = (Category) hibernateTemplate.find("from Category c where c.categoryName=:categoryName").get(0);
			 return fetchedUser;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return fetchedUser;
		}	
	}


	@Override
	public List<User> getAllUser() {
		return hibernateTemplate.loadAll(User.class);
	}

	@Override
	public boolean deleteUser(User user) {
		try {
			hibernateTemplate.delete(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		try {
			hibernateTemplate.update(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
