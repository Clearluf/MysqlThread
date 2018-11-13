package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class simpleConnectionpool {
	/**
	 * 用户名
	 */
	private static String user;
	/**
	 * 密码
	 */
	private static String password;
	/**
	 * 连接数据库的URL
	 */
	private static String url;

	/**
	 * 连接池 
	 */
	private static LinkedList<Connection> pool;

	/*
	 * 初始化连接池
	 */
	static {
		try {

			pool = new LinkedList<Connection>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/mytest?characterEncoding=UTF-8&serverTimezone=GMT%2B8";
			user = "root";
			password = "qwer123";

			// 创建10个连接对象（包装类对象）放到池子中，即连接池的最大连接为10个
			for (int i = 0; i < 10; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				Connection connectionWrapper = new ConnectionWapper(connection, pool);
				pool.add(connectionWrapper);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @throws SQLException
	 * @method 向外提供连接对象
	 */
	public Connection getConnection() throws SQLException {

		Connection connection;
		if (pool.size() > 0) {
			connection = pool.removeFirst();
		} else {
			// 等待超时，返回一个新创建的对象
			connection = DriverManager.getConnection(url, user, password);
		}
		System.out.println("当前池子中有  " + pool.size() + " 个对象");
		return connection;
	}

	/**
	 * 归还连接对象 直接简化在包装类的close方法中
	*/

}
