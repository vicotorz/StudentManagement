package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * 
 * 有关驱动的一些操作
 */

public class jdbcmanager {
	// ========================================注册驱动==================================//

	private static Connection conn;
	static String driver;
	static String url;
	static String user;
	static String pwd;
	static {
		ResourceBundle rb = ResourceBundle.getBundle("com");
		driver = rb.getString("oracle.driver");
		url = rb.getString("oracle.url");
		user = rb.getString("oracle.user");
		pwd = rb.getString("oracle.pwd");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.out.println("注册失败！");
		}
	}

	// ========================================获得连接==================================//
	public static Connection getConn() {

		try {
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// ========================================关闭连接===================================//

	public static void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				
				// e.printStackTrace();
				conn = null;
			}
		}

	}

}
