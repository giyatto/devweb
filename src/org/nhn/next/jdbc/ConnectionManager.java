package org.nhn.next.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/giyatto_db?useUnicode=true&characterEncoding=utf8";
		String id = "giyatto";
		String pw = "aa";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			return DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			return null;
		}
	}
}
