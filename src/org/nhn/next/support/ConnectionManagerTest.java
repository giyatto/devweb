package org.nhn.next.support;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

public class ConnectionManagerTest {

	@Test
	public void test() {
		Connection con = ConnectionManager.getConnection();
		assertNotNull(con);
	}

}
