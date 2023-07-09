package com.example.Redis.service;

import java.util.List;

import com.example.Redis.model.User;

public interface UserService {

	boolean saveUser(User user);

	List<User> fetchAllUser();

	User fetchUserByid(Long id);

	boolean deleteUserByid(Long id);

	boolean updateUserByid(Long id ,User user);

}
