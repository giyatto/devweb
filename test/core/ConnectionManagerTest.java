package core;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

import core.jdbc.ConnectionManager;

public class ConnectionManagerTest {

	@Test
	public void test() {
		Connection con = ConnectionManager.getConnection();
		assertNotNull(con);
	}

}
