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

import com.eshopee.webservice.dao.OrderDao;
import com.eshopee.webservice.exception.OrderNotFound;
import com.eshopee.webservice.model.Order;

@RestController
public class OrderController {

	@Autowired
	OrderDao orderDao;

	@GetMapping("/order")
	public Order getOrderByName(@RequestParam("name") String name) throws OrderNotFound {
		Order order = orderDao.findByName(name);
		if (order != null) {
			return order;
		}
		throw new OrderNotFound("Order Not Found with Given Name = " + name);
	}

	@GetMapping("/orders")
	public List<Order> getOrders() throws OrderNotFound {

		List<Order> list = orderDao.findAll();
		if (list.isEmpty()) {
			throw new OrderNotFound("Order list is empty, Zero record found !");

		}
		return list;
	}

	@GetMapping("/orders/{id}")
	public Order getOrderById(@PathVariable("id") int id) throws OrderNotFound {
		Order order = orderDao.findById(id);
		if (order != null) {
			return order;
		}
		throw new OrderNotFound("Order Not Found by Given Id = " + id);
	}

	@PostMapping("/orders")
	public Map<String, String> addOrder(@RequestBody Order order) {
		int rowsAffected = orderDao.insert(order);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "order created successfully !");
		response.put("rowAffected", String.valueOf(rowsAffected));
		return response;
	}

	@PutMapping("/orders/{id}")
	public Map<String, String> updateOtderById(@PathVariable("id") int id, @RequestBody Order order) {
		int rowAffected = orderDao.update(order);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Order updated successfully !");
		response.put("rowAffected", String.valueOf(rowAffected));
		return response;
	}

	@DeleteMapping("/order/{id}")
	public Map<String, String> deleteOrderById(@PathVariable("id") int id) {
		int rowAffected = orderDao.delete(id);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "order deleted successfully !");
		response.put("rowAffected", String.valueOf(rowAffected));
		return response;
	}

}
