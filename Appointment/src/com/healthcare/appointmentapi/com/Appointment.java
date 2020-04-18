package com.healthcare.appointmentapi.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultButtonModel;
//create appointment class
public class Appointment {

	private Connection connect() {
//DB Connection
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/appoinments?serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insetAppointment(String appoinmentId, String docterId, String patientId, String date,
			String payment) {

		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while DB";

			}

			java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
			String query = "insert into appointment (`docter_id`,`patient_id`,`appoinment_id`,`date`,`payment`) values (?,?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, docterId);
			statement.setString(2, patientId);
			statement.setString(3, appoinmentId);
			statement.setDate(4, sqlDate);
			statement.setString(5, payment);

			statement.execute();
			con.close();

		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;

	}

	public String readAppointment() {

		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while DB";

			}

			output = "<table border=\"1\"><tr><th>appointment id</th><th>docter id</th><th>patient id</th><th>date</th><th>payment</th></tr>";

			String query = "select * from appointment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				String appoinmentId = rs.getString("appoinment_id");
				String docterId = rs.getString("docter_id");
				String patientId = rs.getString("patient_id");
				Date date = rs.getDate("date");
				String payment = rs.getString("payment");

				output += "<tr><td>" + appoinmentId + "</td>";
				output += "<td>" + docterId + "</td>";
				output += "<td>" + patientId + "</td>";
				output += "<td>" + date.toString() + "</td>";
				output += "<td>" + payment + "</td>";
			}
			con.close();

		} catch (Exception e) {
			output = "Error reading inserting the item.";
			e.printStackTrace();
		}
		return output;
	}

	public String updateAppointment(String appoinmentId, String docterId, String patientId, String date,
			String payment) {

		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while DB";

			}
			Date dateObj = new SimpleDateFormat("yyyy/MM/dd").parse(date);
			java.sql.Date sqlDate = new java.sql.Date(dateObj.getTime());

			String query = "UPDATE appointment SET docter_id=?,patient_id=?,date=?,payment=? WHERE appoinment_id=?";

//				Date dateObj = new SimpleDateFormat("yyyy/MM/dd").parse(date);s
			PreparedStatement statement = con.prepareStatement(query);
			

			statement.setString(1, docterId);
			statement.setString(2, patientId);
			statement.setDate(3, sqlDate);
			statement.setString(4, payment);
			statement.setString(5, appoinmentId);
			
			System.out.println(statement.toString());
			
			statement.execute();
			con.close();

		} catch (Exception e) {
			output = "Error while inserting the appointment.";
			e.printStackTrace();
		}
		return output;
	}

		public String deleteAppointment(String appoinmentId) {
			
			String output = "";
			try {
				Connection con = connect();
				
				if(con == null) {
					return "Error while DB";
					
				}
				
				String query = "delete from appointment where appoinment_id=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, appoinmentId);
				
				System.out.println(preparedStmt.toString());
				preparedStmt.execute();
				con.close();
				
				output="deleted successfully";
				
			}catch(Exception e) {
				output = "Error while inserting the appointment.";   
				e.printStackTrace();
			}
			return output;  
			
			}

}
