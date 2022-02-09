package com.eshopee.webservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eshopee.webservice.model.Order;

@Repository
public class OrderDao {

	@Autowired
	JdbcTemplate template;

	class OrderMapper implements RowMapper<Order> {

		@Override
		public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
			Order order = new Order();
			order.setId(rs.getInt(1));
			order.setName(rs.getString(2));
			order.setType(rs.getString(3));
			order.setPrice(rs.getDouble(4));
			order.setAddress(rs.getString(5));
			order.setCreatedAt(rs.getDate(6));
			return order;
		}

	}

	public List<Order> findAll() {
		List<Order> orders = new LinkedList<Order>();
		orders = template.query("select * from orders", new OrderMapper());
		return orders;
	}

	public Order findById(int id) {
		return template.queryForObject("select * from orders where id=?", new OrderMapper(), id);

	}

	public Order findByName(String name) {
		return template.queryForObject("select * from orders where name=?", new OrderMapper(), name);

	}

	public int insert(Order order) {
		return template.update("insert into orders(id,name,type,price,address)" + "values(?, ?, ?, ?, ?)",
				new Object[] { order.getId(), order.getName(), order.getType(), order.getPrice(), order.getAddress() });

	}

	public int update(Order order) {
		return template.update("update orders " + "set name=?, type=?, price=?, address=? " + "where id=?",
				new Object[] { order.getName(), order.getType(), order.getPrice(), order.getAddress(), order.getId() });

	}

	public int delete(int id) {
		return template.update("delete from orders where id = ?", id);
	}

}
