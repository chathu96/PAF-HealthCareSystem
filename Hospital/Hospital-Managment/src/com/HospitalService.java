package com;

import model.Hospital;

//For REST Service 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/hospitals")
public class HospitalService {
	Hospital hospitalObj = new Hospital();

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String readDoctors() {
		return hospitalObj.readHospitals();
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body><h1>" + "Hello Jersey" + "</body></h1>"
				+ "</html> ";
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospitals(@FormParam("Hospital_Name") String Hospital_Name,
			@FormParam("Hospital_PhoneNo") String Hospital_PhoneNo, @FormParam("Hospital_City") String Hospital_City,
			@FormParam("Hospital_Email") String Hospital_Email, @FormParam("Hospital_Address") String Hospital_Address)

	{
		String output = hospitalObj.insertHospitals(Hospital_Name, Hospital_Address, Hospital_PhoneNo, Hospital_City,
				Hospital_Email);
		return output;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospitals(String HospitalsData, String Hospital_Email, int Hospital__ID, String Hospital_Name) {
		// Convert the input string to a JSON object
		JsonObject hospitalObject = new JsonParser().parse(HospitalsData).getAsJsonObject();

		// Read the values from the JSON object
		String Hospital_ID = hospitalObject.get("Hospital_ID").getAsString();
		String Hosptal_Name = hospitalObject.get("Hospital_Name").getAsString();
		String Hospital_Address = hospitalObject.get("Hospital_Address").getAsString();
		String Hospital_PhoneNo = hospitalObject.get("Hospital_PhoneNo").getAsString();
		String Hospital_City = hospitalObject.get("Hospital_City").getAsString();
		String Doctor_Email = hospitalObject.get("Hospital_Email").getAsString();

		String output = hospitalObj.updateHospitals(Hospital__ID, Hospital_Name, Hospital_Address, Hospital_PhoneNo,
				Hospital_City, Hospital_Email);

		return output;
	}

	@DELETE
	@Path("/{param}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospitals(@PathParam("param") int id) {
		String output = hospitalObj.deleteHospitals(id);

		return output;
	}
}
