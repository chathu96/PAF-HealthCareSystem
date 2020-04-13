package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class doctor {
	//A common method to connect to the DB 
	private Connection connect() {
		Connection con = null;
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 //Provide the correct details: DBServer/DBName, username, password 
			 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaredb", "root", "");

			//For testing          
			 System.out.print("Successfully connected");
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con; 
	}
	
	public String readDoctors() {  
		String output = "";  


		try {  
			Connection con = connect();  
			if (con == null)  {   
				return "Error while connecting to the database for reading.";  
			} 

		// Prepare the html table to be displayed   
		output = "<table border=\"1\"><tr><th>Doctor Name</th>"    +""
				+ "<th>Doctor_Specialization</th><th>Specialization</th>"    + ""
				+ "<th>Doctor_MedicalRegistrationNo</th><th>Doctor Medical Registration No</th>"    + ""
				+ "<th>Doctor_ContactNo</th><th>Doctor Contact No</th>"    + ""
				+ "<th>Doctor_Address</th><th>Doctor Address</th>"    + ""
				+ "<th>Doctor_Email</th><th>Doctor Email</th>"    + ""
				+ "<th>Doctor_NIC</th><th>Doctor NIC</th>"    + ""
						+ "<th>Update</th><th>Remove</th></tr>"; 

		  String query = "select * from doctors";   
		  Statement stmt = con.createStatement();   
		  ResultSet rs = stmt.executeQuery(query); 

		  // iterate through the rows in the result set   
		  while (rs.next())   {    
			  String Doctor_ID = Integer.toString(rs.getInt("Doctor_ID"));    
			  String Doctor_Name = rs.getString("Doctor_Name");    
			  String Doctor_Specialization = rs.getString("Doctor_Specialization");    
			  String Doctor_MedicalRegistrationNo = rs.getString("Doctor_MedicalRegistrationNo");    
			  String Doctor_ContactNo = rs.getString("Doctor_ContactNo"); 
			  String Doctor_Address = rs.getString("Doctor_Address");
			  String Doctor_Email = rs.getString("Doctor_Email");
			  String Doctor_NIC = Integer.toString(rs.getInt("Doctor_NIC"));  

		   // Add into the html table    
		  output += "<tr><td>" + Doctor_Name + "</td>";    
		  output += "<td>" + Doctor_Specialization + "</td>";
		  output += "<td>" + Doctor_MedicalRegistrationNo + "</td>";    
		  output += "<td>" + Doctor_ContactNo + "</td>"; 
		  output += "<td>" + Doctor_Address + "</td>";    
		  output += "<td>" + Doctor_Email + "</td>";
		  output += "<td>" + Doctor_NIC + "</td>"; 

		   // buttons    
		  output += "<td><input name=\"btnUpdate\" "     + " "
		  		+ "type=\"button\" value=\"Update\"></td>"     + ""
		  				+ "<td><form method=\"post\" action=\"doctors.jsp\">"     + ""
		  						+ "<input name=\"btnRemove\" "     + " "
		  								+ "type=\"submit\" value=\"Remove\">"     + ""
		  										+ "<input name=\"Doctor_ID\" type=\"hidden\" "     + " "
		  												+ "value=\"" + 
		  												Doctor_ID + "\">" + "</form></td></tr>";   
		  } 

		  con.close(); 

		  // Complete the html table   
		  output += "</table>"; 
		}
		catch (Exception e) {  
			output = "Error while reading the Hospital data.";  
			System.err.println(e.getMessage()); 
		}

		return output;
	}
	
	public String insertDoctors(String Doctor_Name, String Doctor_Specialization, String Doctor_MedicalRegistrationNo, String Doctor_ContactNo, String Doctor_Address, String Doctor_Email, String Doctor_NIC) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement   
			String query = " insert into hospitals (`Doctor_Name`,`Doctor_Specialization`,`Doctor_MedicalRegistrationNo`,`Doctor_ContactNo`,`Doctor_Address`,`Doctor_Email`,`Doctor_NIC`)"+" values (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values 
			preparedStmt.setInt(1, 0);   
			preparedStmt.setString(2, Doctor_Name);   
			preparedStmt.setString(3, Doctor_Specialization);    
			preparedStmt.setString(4, Doctor_MedicalRegistrationNo);
			preparedStmt.setString(5, Doctor_ContactNo);
			preparedStmt.setString(6, Doctor_Address);
			preparedStmt.setString(7, Doctor_Email);
			preparedStmt.setString(8, Doctor_NIC);

			//execute the statement   
			preparedStmt.execute();   
			con.close(); 

			output = "Inserted successfully";
		}
		catch (Exception e) {   
			output = "Error while inserting the Doctors.";   
			System.err.println(e.getMessage());  
		} 

		 return output; 
	}
	
	public String updateDoctors(String Doctor_ID, String Doctor_Name, String Doctor_Specialization, String Doctor_MedicalRegistrationNo, String Doctor_ContactNo, String Doctor_Address, String Doctor_Email, String Doctor_NIC)  {   
		String output = ""; 
	 
	  try   {   
		  Connection con = connect(); 
	 
		  if (con == null)    {
			  return "Error while connecting to the database for updating."; 
		  } 
	 
	   // create a prepared statement    
	   String query = "UPDATE doctors SET Doctor_Name=?,Hospital_Address=?,Doctor_Specialization=?,Doctor_MedicalRegistrationNo=?,Doctor_ContactNo=?,Doctor_Address=?,Doctor_Email=?,Doctor_NIC=?      "
	   		+ "			WHERE Doctor_ID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
		preparedStmt.setString(1, Doctor_Name);   
		preparedStmt.setString(2, Doctor_Specialization);    
		preparedStmt.setString(3, Doctor_MedicalRegistrationNo);
		preparedStmt.setString(4, Doctor_ContactNo);
		preparedStmt.setString(5, Doctor_Address);
		preparedStmt.setString(6, Doctor_Email);
		preparedStmt.setString(7, Doctor_NIC);
	   
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close(); 
	 
	   output = "Updated successfully";   
	   }   catch (Exception e)   {    
		   output = "Error while updating the Doctors.";    
		   System.err.println(e.getMessage());   
	   } 
	 
	  return output;  
	  }
	
	public String deleteDoctors(String Doctor_ID) {  
		String output = ""; 
	 
	 try  {   
		 Connection con = connect(); 
	 
	  if (con == null)   {    
		  return "Error while connecting to the database for deleting.";   
	  } 
	 
	  // create a prepared statement   
	  String query = "delete from doctors where Doctor_ID=?"; 
	 
	  PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	  // binding values   
	  preparedStmt.setInt(1, Integer.parseInt(Doctor_ID));       
	  // execute the statement   
	  preparedStmt.execute();   
	  con.close(); 
	 
	  output = "Deleted successfully";  
	  }  catch (Exception e)  {   
		  output = "Error while deleting the doctor.";   
		  System.err.println(e.getMessage());  
		  
	 } 
	 
	 return output; 
	 }
}
