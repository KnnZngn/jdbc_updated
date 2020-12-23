package com.jdbc.jdbc_demo01;

//1)Import the package
import java.sql.*;

public class JdbcExecuteQueryPractice02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//2)Register the Driver
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	
    	//3)Establish the Connection
    	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
    	
    	//4)Create the Statement
    	Statement st = con.createStatement();
    	
    	//5)Execute the Query:Select the firstname, lastname, and salary of the employee whose salary is the highest from employees table 
    	ResultSet rs1 = st.executeQuery("select first_name, last_name, salary from employees where salary = (select max(salary) from employees)");  	
    	//6)Process the Result
    	while(rs1.next()) {
    		//System.out.println("Country name: " + rs1.getString(1)); also works because we are getting just 1 column from the query
    		System.out.println(rs1.getString(1) + " " + rs1.getString(2) + " " + rs1.getInt(3));
    	}
    	
    	System.out.println("================================");
    	
    	//5)Execute the Query:Select the firstname, lastname, and salary of the one of the employees whose salary is the second highest from employees table 
    	ResultSet rs2 = st.executeQuery("select first_name, last_name, salary from employees order by salary desc offset 1 row fetch next 1 row only");  	
    	//6)Process the Result
    	while(rs2.next()) {
    		//System.out.println("Country id: " + rs2.getString(1) + " - Country name: " + rs2.getString(2)); 
    		//also works because we are getting 2 columns from the query
    		System.out.println(rs2.getString(1) + " " + rs2.getString(2) + " " + rs2.getInt(3));
    	}
    	
    	System.out.println("================================");
    	
    	//5)Execute the Query:Select the firstname, lastname, and salary of all employees whose salary is the second highest from employees table 
    	ResultSet rs3 = st.executeQuery("select first_name, last_name, salary from employees where salary = (SELECT salary FROM employees ORDER BY salary DESC OFFSET 1 ROW FETCH NEXT 1 ROW ONLY)");  	
    	//6)Process the Result
    	while(rs3.next()) {
    		//System.out.println("Country id: " + rs2.getString(1) + " - Country name: " + rs2.getString(2)); 
    		//also works because we are getting 2 columns from the query
    		System.out.println(rs3.getString(1) + " " + rs3.getString(2) + " " + rs3.getInt(3));
    	}
    	
    	//7) a. Close the Statement   	b. Close the Connection  	c. Close the ResultSet
    	st.close();
    	con.close();
    	rs1.close();
    	rs2.close();
    	rs3.close();


	}

}
