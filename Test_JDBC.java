package com.surya.springmvc.hibernate.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test_JDBC {

	public static void main(String[] args) throws SQLException {
		
		String url="jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
		String username="hbstudent";
		String password="hbstudent";
		
		try {
			System.out.println("connecting to the database ");
			Connection connection=DriverManager.getConnection(url,username,password);
			System.out.println("connection established.. ");
		}
		finally {
			System.out.println("hai");
		}
	}

}
