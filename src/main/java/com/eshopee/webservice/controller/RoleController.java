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

import com.eshopee.webservice.dao.RoleDao;
import com.eshopee.webservice.exception.RoleNotFound;
import com.eshopee.webservice.model.Role;

@RestController
public class RoleController {

	@Autowired
	RoleDao roleDao;

	@GetMapping("/role")
	public Role getRoleByName(@RequestParam("name") String name) throws RoleNotFound {
		Role role = roleDao.findByName(name);
		if (role != null) {
			return role;
		}
		throw new RoleNotFound("Role Not Found with Given Name = " + name);
	}

	@GetMapping("/roles")
	public List<Role> getRoles() throws RoleNotFound {

		List<Role> list = roleDao.findAll();
		if (list.isEmpty()) {
			throw new RoleNotFound("Role list is empty, Zero record found !");

		}
		return list;
	}

	@GetMapping("/roles/{id}")
	public Role getRoleById(@PathVariable("id") int id) throws RoleNotFound {
		Role role = roleDao.findById(id);
		if (role != null) {
			return role;
		}
		throw new RoleNotFound("Role Not Found by Given Id = " + id);
	}

	@PostMapping("/roles")
	public Map<String, String> addRoles(@RequestBody Role role) {
		int rowsAffected = roleDao.insert(role);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Role created successfully !");
		response.put("rowAffected", String.valueOf(rowsAffected));
		return response;
	}

	@PutMapping("/role/{id}")
	public Map<String, String> updateRoleById(@PathVariable("id") int id, @RequestBody Role role) {
		int rowAffected = roleDao.update(role);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Role updated successfully !");
		response.put("rowAffected", String.valueOf(rowAffected));
		return response;
	}

	@DeleteMapping("/role/{id}")
	public Map<String, String> deleteRoleById(@PathVariable("id") int id) {
		int rowAffected = roleDao.delete(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Role deleted successfully !");
		response.put("rowAffected", String.valueOf(rowAffected));
		return response;
	}

}
