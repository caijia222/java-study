package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QueryUtil {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc";
	private static final String JDBC_USER = "learn";
	private static final String JDBC_PASSWORD = "learnpassword";

	public static void main(String[] args) {
		Connection conn = getConnection();
		System.out.println(conn);
	}
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
		} catch (SQLException e) {
			System.out.println("获取数据库连接异常");
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
