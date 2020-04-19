package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Patient 
{

	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/patient?useTimezone=true&serverTimezone=UTC", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	} 

	//insert data
	public String insertPatient(String name, String address, String contactNo, String email, String nic, String gender, String dob)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into patient(`pID`, `pName`, `pAddress`, `contactNo`, `Email`, `NIC`, `Gender`, `DOB`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, address);
			preparedStmt.setInt(4, Integer.parseInt(contactNo));
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, nic);
			preparedStmt.setString(7, gender);
			preparedStmt.setString(8, dob);
			

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the Patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Read data
	public String readPatients()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Patient ID</th><th>Patient Name</th><th>Address</th>"+
					"<th>Contact No</th><th>Email</th><th>NIC</th><th>Gender</th><th>DOB</th></tr>";
			
			String query = "select * from patient";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String pID = Integer.toString(rs.getInt("pID"));
				String pName = rs.getString("pName");
				String pAddress = rs.getString("pAddress");
				String contactNo = Integer.toString(rs.getInt("contactNo"));
				String Email = rs.getString("Email");
				String NIC = rs.getString("NIC");
				String Gender = rs.getString("Gender");
				String DOB =rs.getString("DOB");
				
				// Add into the html table
				output += "<tr><td>" + pID + "</td>";
				output += "<td>" + pName + "</td>";
				output += "<td>" + pAddress + "</td>";
				output += "<td>" + contactNo + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + NIC + "</td>";
				output += "<td>" + Gender + "</td>";
				output += "<td>" + DOB + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the Patient.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//update 
	public String updatePatient(String id, String name, String address, String contactNo, String email, String nic, String gender, String dob)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE patient SET pName=?, pAddress=?, contactNo=?, Email=?, NIC=?, Gender=?, DOB=? WHERE pID=? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, address);
			preparedStmt.setInt(3, Integer.parseInt(contactNo));
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, nic);
			preparedStmt.setString(6, gender);
			preparedStmt.setString(7, dob);
			preparedStmt.setInt(8, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the Patient.";
			System.err.println(e.getMessage());
		}
		return output;
	} 

	//delete 
	public String deletePatient(String pID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from patient where pID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the Patient.";
			System.err.println(e.getMessage());
		}
		return output;
	} 

}
