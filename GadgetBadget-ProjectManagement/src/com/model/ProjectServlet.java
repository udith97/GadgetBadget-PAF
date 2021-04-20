package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ProjectServlet {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertInvestment(String project_type, String project_name, String researcher, String Description) 
	{ 
		 	String output = ""; 
		 
		 	try
		 	{ 
		 			Connection con = connect(); 
					 if (con == null) 
					 {
						 return "Error while connecting to the database for inserting."; } 
					 // create a prepared statement
					 String query = " insert into investment (	project_id , project_type , project_name , researcher , Description)" + " values (?, ?, ?, ?, ?)"; 
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 // binding values
					 preparedStmt.setInt(1, 0); 
					 preparedStmt.setString(2, project_type); 
					 preparedStmt.setString(3, project_name);
					 preparedStmt.setString(4, researcher); 
					 preparedStmt.setString(5, Description);
					
					 
					// execute the statement3
					 preparedStmt.execute(); 
					 con.close(); 
					 output = "Inserted successfully"; 
		 
		 	} 
		 	
		 	catch (Exception e) 
		 	{ 
		 		output = "Error while inserting the Investment."; 
		 		System.err.println(e.getMessage()); 
		 		System.out.println(e);
		 } 
		 return output; 
	 }
	
	
		 	

}
