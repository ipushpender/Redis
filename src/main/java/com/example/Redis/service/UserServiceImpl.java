package com.example.Redis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Redis.model.User;
import com.example.Redis.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean saveUser(User user) {
		return userDao.saveUser(user);
	}

	@Override
	public List<User> fetchAllUser() {
		return userDao.fetchAllUser();
	}

	@Override
	public User fetchUserByid(Long id) {
		return userDao.fetchUserByid(id);
	}

	@Override
	public boolean deleteUserByid(Long id) {
		return userDao.deleteUserByid(id);
	}

	@Override
	public boolean updateUserByid(Long id, User user) {
		return userDao.updateUserByid(id,user);
	}

}
