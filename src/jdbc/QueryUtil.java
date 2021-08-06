package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryUtil {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc";
	private static final String JDBC_USER = "learn";
	private static final String JDBC_PASSWORD = "learnpassword";
	private static Logger logger = LoggerFactory.getLogger(QueryUtil.class);
	
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
	public static void queryByStatement() {
		logger.info("开始执行查询");
		try (Connection conn = getConnection()) {
			try (Statement stmt = conn.createStatement()) {
				try (ResultSet rs = stmt.executeQuery("select * from students")) {
					while (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						int gender = rs.getInt("gender");
						int grade = rs.getInt("grade");
						int score = rs.getInt("score");
						Student stu = new Student(id, name, gender, grade, score);
						logger.info(stu.toString());
					}
				}
			}
		} catch (SQLException e) {
			logger.error("查询异常",e);
		}
	}
	
	public static void queryByPreparedStatement() {
		logger.info("开始执行查询");
		try (Connection conn = getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement("select * from students where id = ?")) {
				stmt.setInt(1, 10);
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						int gender = rs.getInt("gender");
						int grade = rs.getInt("grade");
						int score = rs.getInt("score");
						Student stu = new Student(id, name, gender, grade, score);
						logger.info(stu.toString());
					}
				}
			}
		} catch (SQLException e) {
			logger.error("查询异常",e);
		}
	}
	
	public static void insertByPreparedStatement() {
		logger.info("开始执行插入");
		try (Connection conn = getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement("insert into students(name,gender,grade,score) values (?,?,?,?)")) {
				stmt.setObject(1, "张三");
				stmt.setObject(2, 1);
				stmt.setObject(3, 3);
				stmt.setObject(4, 90);
				int ret = stmt.executeUpdate();
				logger.info("插入执行结果="+ret);
			}
		} catch (SQLException e) {
			logger.error("插入异常",e);
		}
	}
	
	public static void insertAndGetKeyByPreparedStatement() {
		logger.info("开始执行插入");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED); // 事务的隔离级别
			conn.setAutoCommit(false);
			try (PreparedStatement stmt = 
					conn.prepareStatement("insert into students(name,gender,grade,score) values (?,?,?,?)",Statement.RETURN_GENERATED_KEYS)) {
				stmt.setObject(1, "王六");
				stmt.setObject(2, 1);
				stmt.setObject(3, 3);
				stmt.setObject(4, 60);
				int ret = stmt.executeUpdate();
				logger.info("插入执行结果="+ret);
				try(ResultSet rs = stmt.getGeneratedKeys()){
					while(rs.next()) {
						long id = rs.getLong(1);
						logger.info("生成id=" + id);
					}
				}
			}
			conn.commit();
		} catch (SQLException e) {
			logger.error("插入异常",e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("回滚异常",e);
			}
		}finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				logger.error("设置自动提交异常",e);
			}
		}
	}

	public static void insertBatchByPreparedStatement() {
		logger.info("开始执行插入");
		try (Connection conn = getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement("insert into students(name,gender,grade,score) values (?,?,?,?)")) {
				stmt.setObject(1, "张三");
				stmt.setObject(2, 1);
				stmt.setObject(3, 3);
				stmt.setObject(4, 90);
				stmt.addBatch();
				stmt.setObject(1, "王五");
				stmt.setObject(2, 1);
				stmt.setObject(3, 3);
				stmt.setObject(4, 68);
				stmt.addBatch();
				int[] executeBatch = stmt.executeBatch();
				logger.info("插入执行结果="+Arrays.toString(executeBatch));
			}
		} catch (SQLException e) {
			logger.error("插入异常",e);
		}
	}
	
	public static void updateByPreparedStatement() {
		logger.info("开始执行更新");
		try (Connection conn = getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement("update students set score = ? where name = ?")) {
				stmt.setObject(1, 88);
				stmt.setObject(2, "王六");
				int ret = stmt.executeUpdate();
				logger.info("更新执行结果="+ret);
			}
		} catch (SQLException e) {
			logger.error("更新异常",e);
		}
	}
	
	public static void release(AutoCloseable... autoCloseables) {
		for (AutoCloseable autoCloseable : autoCloseables) {
			try {
				autoCloseable.close();
			} catch (Exception e) {
				logger.error("资源关闭失败",e);
			}
		}
	}
	
}
