package com.eshopee.webservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshopee.webservice.dao.UserDao;
import com.eshopee.webservice.exception.ProductNotFound;
import com.eshopee.webservice.exception.UserNotFound;
import com.eshopee.webservice.model.User;

@RestController
public class UserController {

	@Autowired
	UserDao userDao;

	@GetMapping("/user")
	public User getUserByName(@RequestParam("name") String name) throws UserNotFound {
		User user = userDao.findByName(name);
		if (user != null) {
			return user;
		}
		throw new UserNotFound("User Not Found with Given Name = " + name);
	}

	@GetMapping("/users")
	public List<User> getUsers() throws ProductNotFound, UserNotFound {

		List<User> list = userDao.findAll();
		if (list.isEmpty()) {
			throw new UserNotFound("User list is empty, Zero record found !");

		}
		return list;
	}

	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") int id) throws UserNotFound {
		User user = userDao.findById(id);
		if (user != null) {
			return user;
		}
		throw new UserNotFound("User Not Found by Given Id = " + id);
	}

	@PostMapping("/users")
	public Map<String, String> addUser(@RequestBody User user) {
		int rowsAffected = userDao.insert(user);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "user created successfully !");
		response.put("rowAffected", String.valueOf(rowsAffected));
		return response;
	}

	@PutMapping("/users/{id}")
	public Map<String, String> updateUserById(@PathVariable("id") int id, @RequestBody User user) {
		int rowAffected = userDao.update(user);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "User updated successfully !");
		response.put("rowAffected", String.valueOf(rowAffected));
		return response;
	}

	@DeleteMapping("/user/{id}")
	public Map<String, String> deleteUSerById(@PathVariable("id") int id) {
		int rowAffected = userDao.delete(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "user deleted successfully !");
		response.put("rowAffected", String.valueOf(rowAffected));
		return response;
	}

}
