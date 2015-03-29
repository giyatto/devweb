package org.nhn.next.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SelectJdbcTemplate {
	
	public Object executeQuery(String sql) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			setParameters(pstmt);
			rs = pstmt.executeQuery(); // 데이터를 꺼내와야 되기 때문에.
			return mapRow(rs);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public abstract Object mapRow(ResultSet rs) throws SQLException;
	
	public abstract void setParameters(PreparedStatement pstmt) throws SQLException; 
}
