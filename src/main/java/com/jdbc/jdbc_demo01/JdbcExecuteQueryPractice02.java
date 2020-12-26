package com.jdbc.jdbc_demo01;

import java.sql.*;

public class JdbcExecuteQueryPractice02 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
		
		Statement st = con.createStatement();
		
		//Print name and salary of the worker whose salary is the lowest by using JDBC
		String sql1 = "SELECT first_name, salary\n"
					   + "FROM employees\n"
					   + "WHERE salary = (SELECT MIN(salary) \n"
					   + "                FROM employees)";
		ResultSet rs1 = st.executeQuery(sql1);
		while(rs1.next()) {
			System.out.println(rs1.getString(1) + " - " + rs1.getInt(2));
		}
		
		System.out.println("===================");
		
		//Print name and salary of the worker whose salary is the second highest by using JDBC
		//1.Way: We used subquery, difficult but can be asked in interview
		String sql2 = "SELECT first_name, salary\n"
				      + "FROM employees\n"
				      + "WHERE salary = (SELECT MAX(salary)\n"
				      + "                FROM employees\n"
				      + "                WHERE salary < (SELECT MAX(salary) \n"
				      + "                                FROM employees))";
		ResultSet rs2 = st.executeQuery(sql2);
		while(rs2.next()) {
			System.out.println(rs2.getString(1) + " - " + rs2.getInt(2));
		}
		
		System.out.println("===================");
		
		//2.Way:
		String sql3 = "SELECT first_name, salary\n"
						+ "FROM employees\n"
						+ "ORDER BY salary DESC\n"
						+ "OFFSET 1 ROW\n"
						+ "FETCH NEXT 1 ROW ONLY";
		ResultSet rs3 = st.executeQuery(sql3);
		while(rs3.next()) {
			System.out.println(rs3.getString(1) + " - " + rs3.getInt(2));
		}
		
		System.out.println("===================");
		
		//Print first name, last name, salary of the employees whose salary is less than the average salary
		String sql4 = "SELECT * \n"
						+ "FROM employees\n"
						+ "WHERE salary < (SELECT AVG(salary) \n"
						+ "                FROM employees)";
		ResultSet rs4 = st.executeQuery(sql4);
		while(rs4.next()) {
			System.out.println(rs4.getString(3) + " " + rs4.getString(3) + " --> " + rs4.getInt(8));
		}
		
		System.out.println("===================");
		
		//Print first name, last name, salary of the employees whose salary is between 30 and 60
		//Last name should be in upper case
		String sql5 = "SELECT first_name, UPPER(last_name), salary\n"
						+ "FROM employees\n"
						+ "WHERE department_id BETWEEN 30 AND 60";
		ResultSet rs5 = st.executeQuery(sql5);
		while(rs5.next()) {
			System.out.println(rs5.getString(1) + " " + rs5.getString(2) + " --> " + rs5.getInt(3));
		}
		
		con.close();
		st.close();
		rs1.close();
		rs2.close();
		rs3.close();
		rs4.close();
		rs5.close();

	}

}
