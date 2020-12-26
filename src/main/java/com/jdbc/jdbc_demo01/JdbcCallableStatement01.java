package com.jdbc.jdbc_demo01;

import java.sql.*;

public class JdbcCallableStatement01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	
    	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
    	
    	Statement st = con.createStatement();
    	
    									//1st Example
    	//Create a funtion which uses 2 integer parameters and returns the sum of the integers
		String sql1 = "CREATE OR REPLACE FUNCTION addf(num1 NUMBER, num2 NUMBER)\n"
						+ "RETURN NUMBER IS\n"
						+ "BEGIN\n"
						+ "RETURN num1 + num2;\n"
						+ "END;";
		boolean result1 = st.execute(sql1);
		System.out.println(result1);
		
		//How to call "addf" function by using JDBC
		CallableStatement cst1 = con.prepareCall("{? = call addf(?,?)}");
		cst1.registerOutParameter(1, Types.INTEGER);
		cst1.setInt(2, 10);
		cst1.setInt(3, 20);
		
		cst1.execute();
		System.out.println(cst1.getInt(1));
		
		
									   //2nd Example
		//Create a funtion which returns the name of a student from students table when you enter student id 
		String sql2 = "CREATE OR REPLACE FUNCTION getName(id CHAR)\n"
						+ "RETURN VARCHAR IS\n"
						+ "s_name students.std_name%TYPE;\n"
						+ "s_id students.std_id%TYPE;\n"
						+ "BEGIN\n"
						+ "SELECT std_name \n"
						+ "INTO s_name \n"
						+ "FROM students \n"
						+ "WHERE std_id = id;\n"
						+ "RETURN s_name;\n"
						+ "END;";
		
		boolean result2 = st.execute(sql2);
		System.out.println(result2);
		
		//How to call "getName" function by using JDBC
		CallableStatement cst2 = con.prepareCall("{? = call getName(?)}");
		cst2.registerOutParameter(1, Types.VARCHAR);
		cst2.setString(2, "105");

		cst2.execute();
		System.out.println(cst2.getString(1));
		
		con.close();
    	st.close();
    	cst1.close();
    	cst2.close();
	}
}
