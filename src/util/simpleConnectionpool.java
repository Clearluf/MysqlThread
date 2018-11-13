package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class simpleConnectionpool {
	/**
	 * �û���
	 */
	private static String user;
	/**
	 * ����
	 */
	private static String password;
	/**
	 * �������ݿ��URL
	 */
	private static String url;

	/**
	 * ���ӳ� 
	 */
	private static LinkedList<Connection> pool;

	/*
	 * ��ʼ�����ӳ�
	 */
	static {
		try {

			pool = new LinkedList<Connection>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			url = "jdbc:mysql://localhost:3306/mytest?characterEncoding=UTF-8&serverTimezone=GMT%2B8";
			user = "root";
			password = "qwer123";

			// ����10�����Ӷ��󣨰�װ����󣩷ŵ������У������ӳص��������Ϊ10��
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
	 * @method �����ṩ���Ӷ���
	 */
	public Connection getConnection() throws SQLException {

		Connection connection;
		if (pool.size() > 0) {
			connection = pool.removeFirst();
		} else {
			// �ȴ���ʱ������һ���´����Ķ���
			connection = DriverManager.getConnection(url, user, password);
		}
		System.out.println("��ǰ��������  " + pool.size() + " ������");
		return connection;
	}

	/**
	 * �黹���Ӷ��� ֱ�Ӽ��ڰ�װ���close������
	*/

}
