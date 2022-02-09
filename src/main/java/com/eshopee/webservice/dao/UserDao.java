package com.eshopee.webservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eshopee.webservice.model.User;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate template;

	class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt(1));
			user.setName(rs.getString(2));
			user.setEmail(rs.getString(3));
			user.setPhone(rs.getString(4));
			user.setCreatedAt(rs.getDate(5));
			return user;
		}

	}

	public List<User> findAll() {
		List<User> users = new LinkedList<User>();
		users = template.query("select * from users", new UserMapper());
		return users;
	}

	public User findById(int id) {
		return template.queryForObject("select * from users where id=?", new UserMapper(), id);

	}

	public User findByName(String name) {
		return template.queryForObject("select * from users where name=?", new UserMapper(), name);

	}

	public int insert(User user) {
		return template.update("insert into users(id,name,email,phone)" + "values(?, ?, ?, ?)",
				new Object[] { user.getId(), user.getName(), user.getEmail(), user.getPhone(), });

	}

	public int update(User user) {
		return template.update("update users " + "set name=?, email=?, phone=? " + "where id=?",
				new Object[] { user.getName(), user.getEmail(), user.getPhone(), user.getId() });

	}

	public int delete(int id) {
		return template.update("delete from users where id = ?", id);
	}

}
