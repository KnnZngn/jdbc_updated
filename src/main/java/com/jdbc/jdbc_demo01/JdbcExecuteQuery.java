package com.jdbc.jdbc_demo01;
/*
	We have 7 steps for JDBC to do
*/

//1)Import the package ==> I have iPhone11 
import java.sql.*;//1)Import the package ==> Have a mobile phone

public class JdbcExecuteQuery {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		// 2)Register the Driver ==> I registered to AT&T and have SIM card
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 3)Establish the Connection ==> I called my friend
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");

		// 4)Create the Statement ==> I started to talk
		Statement st = con.createStatement();

		// 1st Example
		//5.Step: Execute the query and store the result ==> Get the sentences into your brain
		ResultSet rs1 = st.executeQuery("SELECT * FROM workers");

		//6. Step: Process the result ==> Do what your friend asked
		while (rs1.next()) {
			System.out.println(rs1.getString(1) + " - " + rs1.getString(2) + " - " + rs1.getInt(3));
		}

		// 2nd Example
		//5.Step: Execute the query and store the result ==> Get the sentences into your brain
		ResultSet rs2 = st.executeQuery("SELECT country_name FROM countries WHERE region_id > 2");

		//6. Step: Process the result ==> Do what your friend asked
		while (rs2.next()) {
			System.out.println(rs2.getString(1));
		}

		// 3rd Example
		//5.Step: Execute the query and store the result ==> Get the sentences into your brain
		ResultSet rs3 = st.executeQuery("CREATE TABLE workers2(id char(2), name VARCHAR2(50))");

		//7.Step: Close everything
		con.close();
		st.close();
		rs1.close();
		rs2.close();
		rs3.close();

	}
}
