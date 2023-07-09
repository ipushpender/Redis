package com.example.Redis.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.Redis.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private RedisTemplate redisTemplate;
	
	private static final String KEY ="USER";
	
	@Override
	public boolean saveUser(User user) {
		try {
			redisTemplate.opsForHash().put(KEY, user.getId().toString(), user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
	}

	@Override
	public List<User> fetchAllUser() {
		return redisTemplate.opsForHash().values(KEY);
	}

	@Override
	public User fetchUserByid(Long id) {
		return (User) redisTemplate.opsForHash().get(KEY, id.toString());
	}

	@Override
	public boolean deleteUserByid(Long id) {
		try {
			redisTemplate.opsForHash().delete(KEY, id.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUserByid(Long id, User user) {
		try {
			redisTemplate.opsForHash().put(KEY, id.toString(), user);

//			not to update only insert if absent
//			redisTemplate.opsForHash().putIfAbsent(KEY, id.toString(), user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
