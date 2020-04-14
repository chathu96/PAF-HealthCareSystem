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
	public String insertDoctors(@FormParam("Doctor_ID") String Doctor_ID,
							@FormParam("Doctor_Name") String Doctor_Name,    
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
	
}
