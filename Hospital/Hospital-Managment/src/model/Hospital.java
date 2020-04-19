
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.PathParam;

public class Hospital {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}

		return con;
	}

	public String insertHospitals(String Hospital_Name, String Hospital_Address, String Hospital_PhoneNo,
			String Hospital_City, String Hospital_Email) {
		String output = "";

		try {
			Connection con = connect();

			// create a prepared statement
			String query = "insert into hospital (`Hospital_Name`,`Hospital_Address`,`Hospital_PhoneNo`,`Hospital_City`,`Hospital_Email`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Hospital_Name);
			preparedStmt.setString(2, Hospital_Address);
			preparedStmt.setString(3, Hospital_PhoneNo);
			preparedStmt.setString(4, Hospital_City);
			preparedStmt.setString(5, Hospital_Email);

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Inserted successfully";
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}

		return output;
	}

	public String readHospitals() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Hospital Name</th>" + "" + "<th>Address</th>" + ""
					+ "<th>Hospital Phone No</th>" + "" + "<th>Hospital City</th>" + "" + "<th>Hospital Email</th>" + ""
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from hospital";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				int Hospital_ID = rs.getInt("Hospital_ID");
				String Hospital_Name = rs.getString("Hospital_Name");
				String Hospital_Address = rs.getString("Hospital_Address");
				String Hospital_PhoneNo = rs.getString("Hospital_PhoneNo");
				String Hospital_City = rs.getString("Hospital_City");
				String Hospital_Email = rs.getString("Hospital_Email");

				// Add into the html table
				output += "<tr><td>" + Hospital_Name + "</td>";
				output += "<td>" + Hospital_Address + "</td>";
				output += "<td>" + Hospital_PhoneNo + "</td>";
				output += "<td>" + Hospital_City + "</td>";
				output += "<td>" + Hospital_Email + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" " + " " + "type=\"button\" value=\"Update\"></td>" + ""
						+ "<td><form method=\"post\" action=\"Hospital.jsp\">" + "" + "<input name=\"btnRemove\" " + " "
						+ "type=\"submit\" value=\"Remove\">" + "" + "<input name=\"Hospital_ID\" type=\"hidden\" "
						+ " " + "value=\"" + Hospital_ID + "\">" + "</form></td></tr>";
			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}

		return output;
	}

	public String updateHospitals(int Hospital_ID, String Hospital_Name, String Hospital_Address,
			String Hospital_PhoneNo, String Hospital_City, String Hospital_Email) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE Hospital SET Hospital_Name=?,Hospital_Address=?,Hospital_PhoneNo=?,Hospital_City=?,Hospital_Email=?      "
					+ "			WHERE Hospital_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Hospital_Name);
			preparedStmt.setString(2, Hospital_Address);
			preparedStmt.setString(3, Hospital_PhoneNo);
			preparedStmt.setString(4, Hospital_City);
			preparedStmt.setString(5, Hospital_Email);

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Hospitals.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteHospitals(int id) {
		String output = "";

		try {
			Connection con = connect();

			// create a prepared statement
			String query = "delete from Hospital where Hospital_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, id);
			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (SQLException e) {
			throw new IllegalStateException(e);

		}

		return output;
	}
}
