package com.eshopee.webservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eshopee.webservice.model.Role;

@Repository
public class RoleDao {

	@Autowired
	JdbcTemplate template;

	class RoleMapper implements RowMapper<Role> {

		@Override
		public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
			Role role = new Role();
			role.setId(rs.getInt(1));
			role.setRole(rs.getString(2));
			role.setCreaedAt(rs.getDate(3));
			return role;
		}

	}

	public List<Role> findAll() {
		List<Role> roles = new LinkedList<Role>();
		roles = template.query("select * from roles", new RoleMapper());
		return roles;
	}

	public Role findById(int id) {
		return template.queryForObject("select * from roles where id=?", new RoleMapper(), id);

	}

	public Role findByName(String name) {
		return template.queryForObject("select * from roles where role=?", new RoleMapper(), name);

	}

	public int insert(Role role) {
		return template.update("insert into roles(id,role)" + "values(?, ?)",
				new Object[] { role.getId(), role.getRole(), });

	}

	public int update(Role role) {
		return template.update("update roles " + "set role=? " + "where id=?",
				new Object[] { role.getRole(), role.getId() });

	}

	public int delete(int id) {
		return template.update("delete from roles where id = ?", id);
	}

}
