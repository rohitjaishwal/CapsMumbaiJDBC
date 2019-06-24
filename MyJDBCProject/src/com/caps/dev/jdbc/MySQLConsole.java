package com.caps.dev.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.Driver; //required for jdbc to connect to mySql database


public class MySQLConsole {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//1
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");

			//2
			String dbUrl="jdbc:mysql://localhost:3306/caps_mumbai"
					+ "?user=root&password=root";
			con = DriverManager.getConnection(dbUrl); 
			System.out.println("Connected...");
			
			//3&4
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Query:");
			String query = sc.nextLine();
			sc.close();

			stmt = con.createStatement();
			
			boolean b = stmt.execute(query);
			int count;
			
			if(b) {
				rs = stmt.getResultSet();
				while(rs.next()){
					int userid = rs.getInt("user_id");
					String username = rs.getString("username");
					String email = rs.getString("email");
					String passwd = rs.getString("password");
					System.out.println(userid);
					System.out.println(username);
					System.out.println(email);
					System.out.println("-------------------");
				}
			}
				else  {
					count = stmt.getUpdateCount();
					System.out.println("Query OK, "+count+" rows affected.");
				}
					
				}
		catch (Exception e) {
			e.printStackTrace();
		}finally {					//5
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(con != null) {
					con.close();
				} 
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}



