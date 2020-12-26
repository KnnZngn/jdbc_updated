package com.jdbc.jdbc_demo01;

import java.sql.*;

public class JdbcPreparedStatement02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	
    	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
    	
    	Statement st = con.createStatement();
    	
		//Use PreparedStatement to update grades by using student ids
		String sql1 = "UPDATE students SET std_grade = ? WHERE std_id = ?";
		
		PreparedStatement pst = con.prepareStatement(sql1);
		//Update grade to 10 if the id is 101
		pst.setInt(1, 10);
		pst.setString(2, "101");
		
		int result1 = pst.executeUpdate();
		System.out.println(result1);
		
    	//Check if the record is updated
    	String sql2 = "SELECT * FROM students";
    	ResultSet rs1 = st.executeQuery(sql2);
		while(rs1.next()) {
			System.out.println(rs1.getString(1) + " - " + rs1.getString(2) + " - " + rs1.getInt(3));
		}
		
		con.close();
		st.close();
		pst.close();
		rs1.close();

		/*
		 	Note: Use this when you plan to use the SQL statements many times. 
		 	      The PreparedStatement interface accepts input parameters at runtime.
		*/

	}

}
