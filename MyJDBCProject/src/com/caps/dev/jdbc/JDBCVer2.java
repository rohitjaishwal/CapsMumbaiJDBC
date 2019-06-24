package com.caps.dev.jdbc;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCVer2 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//Load the Driver
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded...");

			//Get Connection via Driver..
			//Version 3 of getConnection();
			String url = "jdbc:mysql://localhost:3306/caps_mumbai";
			String path = "C:/Users/Rohit/Desktop/Caps/"
					+ "Advanced Java/db.properties";
			FileReader fr = new FileReader(path);
			Properties prop = new Properties();
			prop.load(fr);
			conn = DriverManager.getConnection(url,prop);
			System.out.println("Connection Established....");
			System.out.println("*****************************");

			//Issue the sql queries...
			String query = "SELECT * FROM users_info";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			//Process the results returned...
			while(rs.next()){
				int userid = rs.getInt("user_id");
				String name = rs.getString("username");
				String email = rs.getString("email");
				
				System.out.println(userid);
				System.out.println(name);
				System.out.println(email);
				        
				System.out.println("-------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}finally {        //Close all the JDBC objects...
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(stmt!=null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(rs!=null) {
						try {
							rs.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}
}
