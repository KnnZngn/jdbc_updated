package com.jdbc.jdbc_demo01;

import java.sql.*;

public class JdbcCallableStatement02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	
    	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
    	
    	Statement st = con.createStatement();
    	
    										//1st Example
    	//Create a procedure to find the minimum one of 2 numbers
    	String sql1 = "CREATE OR REPLACE PROCEDURE findMin(x IN number, y IN number, z OUT number) IS \n"
    					+ "BEGIN \n"
    					+ "   IF x < y THEN \n"
    					+ "      z:= x; \n"
    					+ "   ELSE \n"
    					+ "     z:= y; \n"
    					+ "  END IF; \n"
    					+ "END;";
    	
		boolean result1 = st.execute(sql1);
		System.out.println(result1);
    	
    	//How to call "findMin" procedure by using JDBC
    	CallableStatement cst1 = con.prepareCall("{call findMin(?, ?, ?)}");
    	cst1.setInt(1, 100);
		cst1.setInt(2, 23);
    	cst1.registerOutParameter(3, Types.INTEGER);
    	
    	cst1.execute();
    	
    	System.out.println(cst1.getInt(3));
    	
    	System.out.println("======================");
    	
    									//2nd Example
    	//Create a procedure to get the name and grade of a student from students table when you enter student id
    	String sql2 = "CREATE OR REPLACE PROCEDURE getNameGrade(id IN CHAR, name OUT VARCHAR2, grade OUT NUMBER) IS\n"
    					+ "BEGIN\n"
    					+ "SELECT std_name, std_grade  \n"
    					+ "INTO name, grade \n"
    					+ "FROM students \n"
    					+ "WHERE std_id = id;\n"
    					+ "END;";
    	boolean result2 = st.execute(sql2);
		System.out.println(result2);
		
		//How to call "getNameGrade" procedure by using JDBC
		CallableStatement cst2 = con.prepareCall("{call getNameGrade(?, ?, ?)}");
    	cst2.setString(1, "105");
    	cst2.registerOutParameter(2, Types.VARCHAR);
    	cst2.registerOutParameter(3, Types.INTEGER);
    	
    	cst2.execute();
    	
    	System.out.println(cst2.getString(2) + " - " + cst2.getInt(3));
    	
    	con.close();
    	st.close();
    	cst1.close();
    	cst2.close();
    	
	}
}
