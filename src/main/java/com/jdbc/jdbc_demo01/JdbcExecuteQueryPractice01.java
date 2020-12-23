package com.jdbc.jdbc_demo01;

//1)Import the package
import java.sql.*;

public class JdbcExecuteQueryPractice01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//2)Register the Driver
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	
    	//3)Establish the Connection
    	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
    	
    	//4)Create the Statement
    	Statement st = con.createStatement();
    	
    	//5)Execute the Query:Select the country names whose region id's are 1 
    	ResultSet rs1 = st.executeQuery("select country_name from Countries where region_id=1");  	
    	//6)Process the Result
    	while(rs1.next()) {
    		//System.out.println("Country name: " + rs1.getString(1)); also works because we are getting just 1 column from the query
    		System.out.println("Country name: " + rs1.getString("country_name"));
    	}
    	
    	System.out.println("================================");
    	
    	//5)Execute the Query:Select the country ids and country names whose region id's are greater than 2 
    	ResultSet rs2 = st.executeQuery("select country_id, country_name from Countries where region_id>2");  	
    	//6)Process the Result
    	while(rs2.next()) {
    		//System.out.println("Country id: " + rs2.getString(1) + " - Country name: " + rs2.getString(2)); 
    		//also works because we are getting 2 columns from the query
    		System.out.println("Country id: " + rs2.getString("country_id") + " - Country name: " + rs2.getString("country_name"));
    	}
    	
    	//7) a. Close the Statement   	b. Close the Connection  	c. Close the ResultSet
    	st.close();
    	con.close();
    	rs1.close();
    	rs2.close();

	}

}
