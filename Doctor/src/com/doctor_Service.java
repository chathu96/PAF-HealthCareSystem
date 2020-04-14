package com;

import model.doctor;

//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON 
import com.google.gson.*; 

//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Doctor") 
public class doctor_Service {  
	doctor doctorObj = new doctor();
	
	@GET  
	@Path("/")  
	@Produces(MediaType.TEXT_HTML)  
	public String readDoctors()  {   
		return doctorObj.readDoctors();
	}
	
	@POST 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertDoctors(@FormParam("Doctor_Name") String Doctor_Name,   
							@FormParam("Doctor_Specialization") String Doctor_Specialization,       
							@FormParam("Doctor_MedicalRegistrationNo") String Doctor_MedicalRegistrationNo,
							@FormParam("Doctor_ContactNo") String Doctor_ContactNo,       
							@FormParam("Doctor_Address") String Doctor_Address,
							@FormParam("Doctor_Email") String Doctor_Email,
							@FormParam("Doctor_NIC") String Doctor_NIC) 
	{  
		String output = doctorObj.insertDoctors(Doctor_Name, Doctor_Specialization, Doctor_MedicalRegistrationNo, Doctor_ContactNo, Doctor_Address, Doctor_Email, Doctor_NIC);  
		return output; 
	}
	
	@PUT 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateDoctors(String DoctorsData) { 
		//Convert the input string to a JSON object  
		JsonObject doctorObject = new JsonParser().parse(DoctorsData).getAsJsonObject(); 
		 
		 //Read the values from the JSON object  
		String Doctor_ID = doctorObject.get("Doctor_ID").getAsString();  
		String Doctor_Name = doctorObject.get("Doctor_Name").getAsString();  
		String Doctor_Specialization = doctorObject.get("Doctor_Specialization").getAsString();  
		String Doctor_MedicalRegistrationNo = doctorObject.get("Doctor_MedicalRegistrationNo").getAsString();  
		String Doctor_ContactNo = doctorObject.get("Doctor_ContactNo").getAsString();
		String Doctor_Address = doctorObject.get("Doctor_Address").getAsString();   
		String Doctor_Email = doctorObject.get("Doctor_Email").getAsString(); 
		String Doctor_NIC = doctorObject.get("Doctor_NIC").getAsString(); 
		 
		String output = doctorObj.updateDoctors(Doctor_ID, Doctor_Name, Doctor_Specialization, Doctor_MedicalRegistrationNo, Doctor_ContactNo, Doctor_Address, Doctor_Email, Doctor_NIC); 
		 
		return output; 
	}
	
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteDoctors(String DoctorsData) {  
		//Convert the input string to an XML document  
		Document doc = Jsoup.parse(DoctorsData, "", Parser.xmlParser());     
		
		//Read the value from the element <Hospital_ID>  
		String Doctor_ID = doc.select("Doctor_ID").text(); 
		 
		 String output = doctorObj.deleteDoctors(Doctor_ID); 
		 
		 return output; 
		 }
}
