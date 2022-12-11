package com.dao;

import java.util.List;

import com.entity.User;

public interface IUserDAO {
	boolean addUser(User user);
	User getUser(int userId);
	List<User> getAllUser();
	boolean deleteUser(User user);
	boolean updateUser(User user);
	User getUser(String email);	
}
