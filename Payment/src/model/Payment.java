package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			//con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", "");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>PayID</th><th>PatientID</th><th>DoctorID</th><th>Card Number</th><th>CVV</th><th>Card type</th><th>Expiry</th>"
					+ "<th>Amount</th></tr>";
			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String payID = Integer.toString(rs.getInt("payID"));
				String patientID = Integer.toString(rs.getInt("patientID"));
				String docID = Integer.toString(rs.getInt("docID"));
				String card_no = rs.getString("card_no");
				String cvv = rs.getString("cvv");
				String card_type = rs.getString("card_type");
				String exp_date = rs.getString("exp_date");
				String amount = Double.toString(rs.getDouble("amount"));

				// Add into the html table
				output += "<tr><td>" + payID + "</td>";
				output += "<td>" + patientID + "</td>";
				output += "<td>" + docID + "</td>";
				output += "<td>" + card_no + "</td>";
				output += "<td>" + cvv + "</td>";
				output += "<td>" + card_type + "</td>";
				output += "<td>" + exp_date + "</td>";
				output += "<td>" + amount + "</td></tr>";
			

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
