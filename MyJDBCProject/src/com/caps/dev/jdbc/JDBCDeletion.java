package com.caps.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBCDeletion {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			//Load the Driver
			java.sql.Driver div = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(div);
			System.out.println("Driver Loaded....");

			//Get the connection via driver
			String dbUrl = "jdbc:mysql://localhost:3306/caps_mumbai";
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the User..");
			String user = sc.nextLine();
			System.out.println("Enter the Password..");
			String password = sc.nextLine();
			conn = DriverManager.getConnection(dbUrl,user,password);
			System.out.println("Connection Est...");
			System.out.println("************************");

			//Issue SQL Query via connection
			String query = "DELETE FROM users_info"
					+ " WHERE user_id=? AND password=?";
			System.out.println("Enter the user_id");
			int userid = Integer.parseInt(sc.nextLine());
			System.out.println("Enter the password");
			String passwd = sc.nextLine();
			pstmt = conn.prepareStatement(query);

			sc.close();

			pstmt.setInt(1, userid);
			pstmt.setString(2, passwd);

			int i = pstmt.executeUpdate();

			//Process the Results..
			if(i>0) {
				System.out.println("Data Deleted....");
			}else {
				System.out.println("Failed to Delete the Data...");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
