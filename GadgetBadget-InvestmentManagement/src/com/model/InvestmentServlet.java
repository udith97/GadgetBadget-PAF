package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InvestmentServlet {

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

	public String insertInvestment(String projectName, String researcherName, String investorName, String investAmount, String cardNo, String cvvNo) 
	{ 
		 	String output = ""; 
		 
		 	try
		 	{ 
		 			Connection con = connect(); 
					 if (con == null) 
					 {
						 return "Error while connecting to the database for inserting."; } 
					 // create a prepared statement
					 String query = " insert into investment (investmentID , projectName , researcherName , investorName , investAmount , cardNo , cvvNo)" + " values (?, ?, ?, ?, ?, ?, ?)"; 
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 // binding values
					 preparedStmt.setInt(1, 0); 
					 preparedStmt.setString(2, projectName); 
					 preparedStmt.setString(3, researcherName);
					 preparedStmt.setString(4, investorName); 
					 preparedStmt.setDouble(5, Double.parseDouble(investAmount)); 
					 preparedStmt.setString(6, cardNo);
					 preparedStmt.setString(7, cvvNo);
					 
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
	
	public String readInvestment() 
	 { 
		String output = ""; 
	 
		try
		{ 
			Connection con = connect(); 
	 
			if (con == null) 
			{
				return "Error while connecting to the database for reading."; } 
	 
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Project Name</th><th>Researcher Name</th>" +
					"<th>Investor Name</th>" + 
					"<th>Invest Amount</th>" +
					"<th>Card Number</th>" +
					"<th>CVV Number</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 
	 
	 
				String query = "select * from investment"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
	 
				// iterate through the rows in the result set
				while (rs.next()) 
				{ 
	 
					String investmentID = Integer.toString(rs.getInt("investmentID")); 
					String projectName = rs.getString("projectName"); 
					String researcherName = rs.getString("researcherName"); 
					String investorName = rs.getString("investorName"); 
					String investAmount = Double.toString(rs.getDouble("investAmount")); 
					String cardNo = Integer.toString(rs.getInt("cardNo")); 
					String cvvNo = Integer.toString(rs.getInt("cvvNo"));
	 
					// Add into the html table
					output += "<tr><td>" + projectName + "</td>"; 
					output += "<td>" + researcherName + "</td>"; 
					output += "<td>" + investorName + "</td>"; 
					output += "<td>" + investAmount + "</td>"; 
					output += "<td>" + cardNo + "</td>";
					output += "<td>" + cvvNo + "</td>";
	 
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='items.jsp'>" 
							+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
											+ "<input name='itemID' type='hidden' value='" + investmentID 
											+ "'>" + "</form></td></tr>"; 
				} 
				con.close(); 
				// Complete the html table
				output += "</table>"; 
	 
		} 
		catch (Exception e) 
	 
		{ 
			output = "Error while reading the investment."; 
			System.err.println(e.getMessage()); 
	 
		} 
		return output; 
	 }
	
	
	public String updateInvestment(String investmentID, String projectName, String researcherName, String investorName, String investAmount, String cardNo, String cvvNo)
	 { 
	 
		String output = ""; 
	
		try
		{ 
			Connection con = connect(); 
	 
			if (con == null) 
			{
				return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement
	 
			String query = "UPDATE investment SET projectName=?,researcherName=?,investorName=?,investAmount=?,cardNo=?,cvvNo=?	"
					+ "WHERE investmentID=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, projectName); 
			preparedStmt.setString(2, researcherName); 
			preparedStmt.setString(3, investorName); 
			preparedStmt.setDouble(4, Double.parseDouble(investAmount)); 
			preparedStmt.setString(5, cardNo); 
			preparedStmt.setString(6, cvvNo); 
			preparedStmt.setInt(7, Integer.parseInt(investmentID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the investment."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	
	public String deleteInvestment(String investmentID) 
	 { 
	 
		String output = ""; 
	 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{
				return "Error while connecting to the database for deleting."; } 
	 
			// create a prepared statement
			String query = "delete from investment where investmentID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(investmentID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
	 
		} 
		catch (Exception e) 
		{ 
			output = "Error while deleting the investment."; 
			System.err.println(e.getMessage()); 
	 
		} 
	
		return output; 
	 } 

}
