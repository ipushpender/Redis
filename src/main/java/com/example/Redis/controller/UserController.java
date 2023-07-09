package com.example.Redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Redis.model.User;
import com.example.Redis.service.UserService;

@RestController
public class UserController {

	@Autowired
	public UserService userService;

	@PostMapping("/user")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		boolean result = userService.saveUser(user);
		return result ? ResponseEntity.ok("User Created") : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@GetMapping("/user")
	public ResponseEntity<List<User>> fetchAllUser() {
		return ResponseEntity.ok(userService.fetchAllUser());
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> fetchUserByid( @PathVariable("id") Long id) {
		return ResponseEntity.ok(userService.fetchUserByid(id));
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<String> updateUserByid( @PathVariable("id") Long id, @RequestBody User user) {
		boolean isupdated = userService.updateUserByid(id,user);
		return  isupdated ?  ResponseEntity.ok("Updated User !!!") : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();	
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUserByid( @PathVariable("id") Long id) {
		boolean isdeleted = userService.deleteUserByid(id);
		return isdeleted ? ResponseEntity.ok("Deleted User Id :: "+id) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
