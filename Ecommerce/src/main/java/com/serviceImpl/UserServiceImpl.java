package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.IUserDAO;
import com.entity.User;
import com.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	
	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.addUser(user);
	}

	@Override
	public User getUser(int userId) {
		// TODO Auto-generated method stub
		return userDAO.getUser(userId);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDAO.getAllUser();
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.deleteUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.updateUser(user);
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		return userDAO.getUser(email);
	}

}
