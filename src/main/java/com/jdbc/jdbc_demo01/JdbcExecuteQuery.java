package com.jdbc.jdbc_demo01;
							/*
								We have 7 steps for JDBC to do
							*/

//1)Import the package ==> I have iPhone11 
import java.sql.*;//1)Import the package ==> Have a mobile phone

public class JdbcExecuteQuery {
	
	
    public static void main( String[] args ) throws ClassNotFoundException, SQLException{
    	
    	//2)Register the Driver ==> I registered to AT&T and have SIM card
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	
    	//3)Establish the Connection ==> I called my friend
    	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
    	
    	//4)Create the Statement ==> I started to talk
    	Statement st = con.createStatement();
    	
    	//5)Execute the Query ==> I understood what my friend told and kept them in my mind
    	ResultSet rs = st.executeQuery("select * from Countries");
    	
    	//6)Process the Result ==> I did what my friend told 
    	while(rs.next()) { // next() puts the pointer before the first row and moves the cursor forward one row from its current position.
    		               // The first call to the method makes the first row the current row; the second call makes the second row the current row, and so on.
    		System.out.println(rs.getString(1) + ": " + rs.getString(2) + " - RegionId:" + rs.getInt(3));
    	}
    	
    	//7) a. Close the Statement   	b. Close the Connection 	c. Close the ResultSet	==> I closed the phone when I finished conversation
    	st.close();
    	con.close();
    	

    }
}
