package com.jdbc.jdbc_demo01;

import java.sql.*;

public class JdbcPreparedStatement01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	
    	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
    	
    	Statement st = con.createStatement();
    	
    	//Create a table whose name is students which has std_id, std_name, std_grade columns
    		//String sql1 = "CREATE TABLE students(std_id CHAR(3), std_name VARCHAR2(50), std_grade NUMBER(2))";
    		//boolean result1 = st.execute(sql1);
    	
    	//Insert 4 records into the students table
			//    	String sql2 = "INSERT ALL INTO students VALUES('101', 'Ali Can', 9)"
			//                		+ "INTO students VALUES('102', 'Veli Han', 12)"
			//                		+ "INTO students VALUES('103', 'Mary Star', 10)"
			//                		+ "INTO students VALUES('104', 'Angie Ocean', 11)"
			//                		+ "SELECT * FROM DUAL";
			//    	int result2 = st.executeUpdate(sql2);
			//    	System.out.println(result2);
    	
    	//Check if the 4 records are inserted
    	String sql3 = "SELECT * FROM students";
    	ResultSet rs1 = st.executeQuery(sql3);
		while(rs1.next()) {
			System.out.println(rs1.getString(1) + " - " + rs1.getString(2) + " - " + rs1.getInt(3));
		}
		
		//Do insertion more dynamic
		String sql4 = "INSERT INTO students VALUES(?, ?, ?)";
		
		PreparedStatement pst = con.prepareStatement(sql4);
			//		pst.setString(1, "105");
			//		pst.setString(2, "Tom Hanks");
			//		pst.setInt(3, 12);
		
		    //int result4 = pst.executeUpdate();
		    //System.out.println(result4);
		
    	//Check if the 5th record is inserted
    	String sql5 = "SELECT * FROM students";
    	ResultSet rs2 = st.executeQuery(sql5);
		while(rs2.next()) {
			System.out.println(rs2.getString(1) + " - " + rs2.getString(2) + " - " + rs2.getInt(3));
		}
		
		//Reuse the PreparedStatement again
		pst.setString(1, "106");
		pst.setString(2, "Eddie Murphy");
		pst.setInt(3, 11);
		
		int result5 = pst.executeUpdate();
		System.out.println(result5);
		
    	//Check if the 6th record is inserted
    	String sql6 = "SELECT * FROM students";
    	ResultSet rs3 = st.executeQuery(sql6);
		while(rs3.next()) {
			System.out.println(rs3.getString(1) + " - " + rs3.getString(2) + " - " + rs3.getInt(3));
		}
		
		con.close();
		st.close();
		pst.close();
		rs1.close();
		rs2.close();
		rs3.close();

	}

}
