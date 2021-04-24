package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class User_Manage {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/user_management", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String createPost(String username, String password) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error";
			}
			

			LocalDate date = LocalDate.now();
			LocalTime time = LocalTime.now();

			String query = "Insert into user_table(username,password,published_date,published_time) values(?,?,?,?) ";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1,username);
			ps.setString(2,password);
			ps.setString(3,date.toString());
			ps.setString(4,time.toString());
			ps.execute();
			con.close();

			output = "Insert Success";
		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while inserting the Item";
			System.err.println(e.getMessage());
		}

		return output;
	}

	//Insert user
	public String read_Post() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error";
			}
			output = "<table border='1'><tr><th>Funding Body ID</th><th>username</th><th>password</th><th>Registered Date</th><th>Registered Time</th>";
			String query = "select * from user_table";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String id = Integer.toString(rs.getInt("iduser"));
				String username = rs.getString("username");
				String password = rs.getString("password");
				String date = rs.getString("published_date");// How to Get Date as A String - Doubt - Solved 2021/04/11
				String time = rs.getString("published_time");// How to Get Date as A String - Doubt - Solved 2021/04/11

				output += "<tr><td>" + id + "</td>";
				output += "<td>" + username + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + time + "</td>";

//				output += "<td><input name=\\\"btnUpdate\\\" type=\\\"button\\\" \r\n"
//						+ " value=\\\"Update\\\" class=\\\"btn btn-secondary\\\"></td>\"\r\n"
//						+ " + \"<td><form method=\\\"post\\\" action=\\\"posts.jsp\\\">\"\r\n"
//						+ " + \"<input name=\\\"btnRemove\\\" type=\\\"submit\\\" value=\\\"Remove\\\" \r\n"
//						+ " class=\\\"btn btn-danger\\\">\"\r\n"
//						+ " + \"<input name=\\\"ID\\\" type=\\\" value=\\\"\" + id\r\n"
//						+ " + \"\\\">\" + \"</form></td></tr>";

			}
			con.close();
			output += "</tabel>";
		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while reading the Posts";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	//update User
	
	public String updatePost(String ID, String username, String password) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			

			LocalDate date = LocalDate.now();
			LocalTime time = LocalTime.now();

			String query = "UPDATE user_table SET username=?,password=?,published_date=?,published_time=? WHERE iduser=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			preparedStmt.setString(3, date.toString());
			preparedStmt.setString(4, time.toString());
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	// delete User
	
	public String deletePost(String ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from user_table where iduser=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}



	
}
