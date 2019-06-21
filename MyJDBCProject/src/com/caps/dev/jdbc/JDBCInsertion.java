package com.caps.dev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import java.sql.PreparedStatement;

public class JDBCInsertion {

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

			//Issue SQL Query via conn
			String query = "INSERT INTO users_info"
					+ " values(?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			System.out.println("Enter the user_id");
			int userid = Integer.parseInt(sc.nextLine());
			System.out.println("Enter the username");
			String username = sc.nextLine();
			System.out.println("Enter the email");
			String email = sc.nextLine();
			System.out.println("Enter the password");
			String passwd = sc.nextLine();
			sc.close();

			pstmt.setInt(1, userid);
			pstmt.setString(2, username);
			pstmt.setString(3, email);
			pstmt.setString(4, passwd);

			int i = pstmt.executeUpdate();

			//Process the Results..
			if(i>0) {
				System.out.println("Data Inserted....");
			}else {
				System.out.println("Failed to Insert the Data...");
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

