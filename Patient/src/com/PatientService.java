package com;

import model.Patient;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/Patients")
public class PatientService {

	Patient patientObj = new Patient();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPatients()
	{
		return patientObj.readPatients();
	}

	//insert data
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("pName") String pName,
			@FormParam("pAddress") String pAddress,
			@FormParam("contactNo") String contactNo,
			@FormParam("Email") String Email,
			@FormParam("NIC") String NIC,
			@FormParam("Gender") String Gender,
			@FormParam("DOB") String DOB)
	{
		String output = patientObj.insertPatient(pName, pAddress, contactNo,  Email, NIC, Gender, DOB);
		return output;
	}

	//update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String patientData)
	{
		//Convert the input string to a JSON object
		JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();

		//Read the values from the JSON object
		String pID = patientObject.get("pID").getAsString();
		String pName = patientObject.get("pName").getAsString();
		String pAddress = patientObject.get("pAddress").getAsString();
		String contactNo = patientObject.get("contactNo").getAsString();
		String Email = patientObject.get("Email").getAsString();
		String NIC = patientObject.get("NIC").getAsString();
		String Gender =patientObject.get("Gender").getAsString();
		String DOB =patientObject.get("DOB").getAsString();

		String output = patientObj.updatePatient(pID, pName, pAddress, contactNo, Email, NIC, Gender, DOB);

		return output;
	}


	//Delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteaPtient(String patientData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		String pID = doc.select("pID").text();

		String output = patientObj.deletePatient(pID);

		return output;
	}
}
