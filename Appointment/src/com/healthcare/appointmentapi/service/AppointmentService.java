package com.healthcare.appointmentapi.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.healthcare.appointmentapi.com.Appointment;
//Appointment Service Class
@Path("appointment")
public class AppointmentService {
	
	  @GET
	    @Produces(MediaType.TEXT_PLAIN)
	    public String getIt() {
	        Appointment appoinment = new Appointment();
	        return  appoinment.readAppointment();
	    }
	    
	    @POST
	    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	    @Produces(MediaType.TEXT_PLAIN)
	    public void insertAppoinment (
	    		@FormParam("docter_id") String docterId,
	    		@FormParam("patient_id") String patientId,
	    		@FormParam("appoinment_id") String appoinmentId,
	    		@FormParam("date") String date,
	    		@FormParam("payment") String payment
	    		) {
	    	
	    	Appointment appoinmentObj = new Appointment();
	    	String output = appoinmentObj.insetAppointment(appoinmentId, docterId, patientId, date, payment);
	    	
	    	
	    	
	    }
	    
	    
	    @PUT 
	    @Consumes(MediaType.APPLICATION_JSON) 
	    @Produces(MediaType.TEXT_PLAIN)
	    public String updateItem(String appointmentData) { 
	    	
	    	//Convert the input string to a JSON object 
	    	JsonObject jsonObject = new JsonParser().parse(appointmentData).getAsJsonObject(); 
	    	 
	    	 //Read the values from the JSON object  
	    	
	    String docter_id = jsonObject.get("docterid").getAsString(); 
	    String patient_id = jsonObject.get("patientid").getAsString(); 
	    String appoinment_id = jsonObject.get("appoinmentid").getAsString(); 
	    String date = jsonObject.get("date").getAsString() ;
	    String payment = jsonObject.get("payment").getAsString(); 
	    
	    Appointment appoinmentObj = new Appointment();
	    	 
	    String output = appoinmentObj.updateAppointment(appoinment_id,docter_id,patient_id,date,payment); 
	    	 
	    	 return output; 
	    	 
	    
	    } 
	    
	    
	    
	    @DELETE
	    @Path("/{id}")
	    public String deleteAppointment(@PathParam("id") String appoinmentId)
	    {  
//	    	JsonObject jsonObject = new JsonParser().parse(appoinmentData).getAsJsonObject(); 
//	        String appoinment_id = jsonObject.get("appoinmentid").getAsString();
	        
	        Appointment appoinmentObj = new Appointment();
	   	 
	        String output = appoinmentObj.deleteAppointment(appoinmentId);
	        	 
	         return output; 


	    	 
	    } 
	    


}
