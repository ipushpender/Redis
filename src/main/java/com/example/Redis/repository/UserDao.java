package com.example.Redis.repository;

import java.util.List;

import com.example.Redis.model.User;

public interface UserDao {

	boolean saveUser(User user);

	List<User> fetchAllUser();

	User fetchUserByid(Long id);

	boolean deleteUserByid(Long id);

	boolean updateUserByid(Long id, User user);

}
