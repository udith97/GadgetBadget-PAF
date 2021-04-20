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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/buyertable", "root", "");
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
					 String query = " insert into buyer (buyerID, buyerName , projectName , email , contactNo , researcherName)" + " values (?, ?, ?, ?, ?, ?)"; 
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, buyerName); 
					 preparedStmt.setString(3, projectName); 
					 preparedStmt.setString(4, email);
					 preparedStmt.setString(5, contactNo); 
					 preparedStmt.setString(6, researcherName); 
					
					 
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

				// iterate through the rows in the result set
				while (rs.next()) 
				{ 
	
					String buyerID = Integer.toString(rs.getInt("buyerID"));
					String buyerName = rs.getString("buyerName"); 
					String projectName = rs.getString("projectName"); 
					String email = rs.getString("email"); 
					String contactNo= rs.getString("contactNo"); 
					String researcherName = rs.getString("researcherName"); 
					
					// Add into the html table
					output += "<tr><td>" + buyerName + "</td>"; 
					output += "<td>" + projectName + "</td>"; 
					output += "<td>" + email + "</td>";  
					output += "<td>" + contactNo + "</td>";
					output += "<td>" + researcherName + "</td>";
	 
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>" 
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
											+ "<input name='itemID' type='hidden' value='" + buyerID 
											+ "'>" + "</form></td></tr>"; 
				} 
				con.close(); 
				// Complete the html table
				output += "</table>"; 
	 
		} 
		catch (Exception e) 
	 
		{ 
			output = "Error while reading the buyer."; 
			System.err.println(e.getMessage()); 
	 
		} 
		return output; 
	 }
	public String updateBuyer(String buyerID, String buyerName, String projectName, String email, String contactNo, String researcherName)
	 { 
	 
		String output = ""; 
	
		try
		{ 
			Connection con = connect(); 
	 
			if (con == null) 
			{
				return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement
	 
			String query = "UPDATE buyer SET buyerName=?, projectName=?,email=?,contactNo=?,researcherName=?"
					+ "WHERE buyerID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, buyerName); 
			preparedStmt.setString(2, projectName); 
			preparedStmt.setString(3, email); 
			preparedStmt.setString(4, contactNo); 
			preparedStmt.setString(5, researcherName);
			preparedStmt.setInt(6, Integer.parseInt(buyerID));
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the buyer."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	
	public String deleteBuyer(String buyerID) 
	 { 
	 
		String output = ""; 
	 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for deleting."; } 
	 
			// create a prepared statement
			String query = "delete from buyer where buyerID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(buyerID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
	 
		} 
		catch (Exception e) 
		{ 
			output = "Error while deleting the buyer."; 
			System.err.println(e.getMessage()); 
	 
		} 
	
		return output; 
	 } 


}
