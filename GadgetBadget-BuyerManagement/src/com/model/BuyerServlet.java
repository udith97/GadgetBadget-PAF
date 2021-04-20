package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BuyerServlet {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public String insertBuyer(String buyerName, String projectName, String email, String contactNo, String researcherName) 
	{ 
		 	String output = ""; 
		 
		 	try
		 	{ 
		 			Connection con = connect(); 
					 if (con == null) 
					 {
						 return "Error while connecting to the database for inserting."; } 
					 // create a prepared statement
					 String query = " insert into buyer (buyerName , projectName , email , contactNo , reseacherName)" + " values (?, ?, ?, ?, ?, ?, ?)"; 
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 // binding values
					 preparedStmt.setString(1, buyerName); 
					 preparedStmt.setString(2, projectName); 
					 preparedStmt.setString(3, email);
					 preparedStmt.setString(4, contactNo); 
					 preparedStmt.setString(5, researcherName); 
					
					 
					// execute the statement3
					 preparedStmt.execute(); 
					 con.close(); 
					 output = "Inserted successfully"; 
		 
		 	} 
		 	catch (Exception e) 
		 	{ 
		 		output = "Error while inserting the Buyer."; 
		 		System.err.println(e.getMessage()); 
		 		System.out.println(e);
		 } 
		 return output; 
	 }
	public String readBuyer() 
	 { 
		String output = ""; 
	 
		try
		{ 
			Connection con = connect(); 
	 
			if (con == null) 
			{
				return "Error while connecting to the database for reading."; } 
	 
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Buyer Name</th>" +
					"<th>Project Name</th>" + 
					"<th>E-mail</th>" +
					"<th>Contact Number</th>" +
					"<th>Researcher Name</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 
	 
	 
				String query = "select * from buyer"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
	

}
