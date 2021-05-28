package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class QueryUtil {

	public static void main(String[] args) throws Exception {
		System.out.println("aaa");
		String url = "jdbc:mysql://localhost:3306/db_cj?serverTimezone=UTC";
		String user = "root";
		String pwd = "123456";
		Connection conn = DriverManager.getConnection(url, user, pwd);
		System.out.println(conn);
		conn.close();
	}
}
