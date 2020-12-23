package com.jdbc.jdbc_demo01;

//1)Import the package
import java.sql.*;

public class JdbcExecuteUpdatePractice01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//2)Register the Driver
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	
    	//3)Establish the Connection
    	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
    	
    	//4)Create the Statement
    	Statement st = con.createStatement();
    	
    	//5)Execute the Query: Update the math grade to 66 whose id is 1001 from Students13
    	int ur1 = st.executeUpdate("Update Students13 SET mathGrade = 66 WHERE id = 1001");
    	System.out.println("The number of rows affected: " + ur1);
    	
    	ResultSet rs1 = st.executeQuery("select * from Students13 where id=1001");    	
    	//6)Process the Result
    	rs1.next();//For single results no need to use while loop but if you use it works
    	System.out.println(rs1.getInt(1) + ": " + rs1.getInt(2) + " - RegionId:" + rs1.getString(3));
    	
    	System.out.println("=========================");
    	
    	//5)Execute the Query: Update the math grade to 100 whose math grade is greater than 90 from Students13
    	int ur2 = st.executeUpdate("Update Students13 SET mathGrade = 100 WHERE mathGrade>80");
    	System.out.println("The number of rows affected: " + ur2);
    	
    	ResultSet rs2 = st.executeQuery("select * from Students13 where mathGrade>90");    	
    	//6)Process the Result
    	rs2.next();//For single results no need to use while loop but if you use it works
    	System.out.println(rs2.getInt(1) + ": " + rs2.getInt(2) + " - RegionId:" + rs2.getString(3));

    	System.out.println("=========================");
    	
    	//5)Execute the Query: Insert into students13 id is 1006, mathGrade is 77 and name is 'Seyhan San'
    	int ir = st.executeUpdate("insert into students13 values(1007, 88, 'Turkan Turk')");
    	System.out.println("The number of rows affected: " + ir);
    	
    	ResultSet rs3 = st.executeQuery("select * from students13 where id=1006");    	
    	//6)Process the Result
    	rs3.next();
    	System.out.println(rs3.getInt(1) + " " + rs3.getInt(2) + " " + rs3.getString(3));
    	

    	//7) a. Close the Statement   	b. Close the Connection  	c. Close the ResultSet
    	st.close();
    	con.close();

	}

}
