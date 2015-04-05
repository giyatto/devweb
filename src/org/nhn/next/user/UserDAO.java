package org.nhn.next.user;

import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class UserDAO {
	public void addUser(User user) {
		JdbcTemplate template = new JdbcTemplate();
		String sql = "insert into USERS values(?,?,?,?)";
		template.executeUpdate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public User findByUserId(String userId) {
//		RowMapper<User> rm = new RowMapper<User>() {
//			@Override
//			public User mapRow(ResultSet rs) throws SQLException {
//				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
//				        rs.getString("email"));
//			}
//		};

		RowMapper<User> rm = rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));	// 람다표현식
		
		JdbcTemplate template = new JdbcTemplate();
		String sql = "select * from USERS where userId= ?";
		return template.executeQuery(sql, rm, userId);
	}

	public void removeUser(String userId) {
		JdbcTemplate template = new JdbcTemplate();
		String sql = "delete from USERS where userId = ?";
		template.executeUpdate(sql, userId);
	}

	public void updateUser(User user) {
		JdbcTemplate template = new JdbcTemplate();
		String sql = "update USERS set password = ?, name = ?, email = ? where userId = ?";
		template.executeUpdate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}

	public List<User> findUsers() {
//		RowMapper<User> rm = new RowMapper<User>() {
//			@Override
//			public User mapRow(ResultSet rs) throws SQLException {
//				return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
//				        rs.getString("email"));
//			}
//		};
		
		RowMapper<User> rm = rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));	// 람다표현식
		
		JdbcTemplate template = new JdbcTemplate();
		String sql = "select * from USERS";
		return template.list(sql, rm);
	}
}
