package com.example.myspring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.example.myspring.domain.User;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int getMatchCount(String userName, String password) {
		String sqlStr = "SELECT count(user_id) FROM t_user WHERE user_name = ? and password =?";
		int count = jdbcTemplate.query(sqlStr, new Object[] { userName, password }, new ResultSetExtractor<Integer>() {

			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				return rs.getInt(1);
			}

		});
		return count;
	}

	public User findUserByName(final String userName) {
		String sqlStr = "SELECT user_id, user_name, credits FROM t_user WHERE user_name = ?";
		final User user = new User();
		jdbcTemplate.query(sqlStr, new Object[] { userName }, new RowCallbackHandler() {

			public void processRow(ResultSet rs) throws SQLException {
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(userName);
				user.setCredits(rs.getInt("credits"));
			}

		});
		return user;
	}

	public void updateLoginInfo(User user) {
		String sqlStr = "UPDATE t_user set last_visit = ?, last_ip = ?, credits = ? WHERE user_id =?";
		jdbcTemplate.update(sqlStr, new Object[] { user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId() });
	}
}
