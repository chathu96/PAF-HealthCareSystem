package com.healthcare.doctorapi.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.healthcare.doctorapi.beans.Doctor;
import com.healthcare.doctorapi.beans.Doctors;

@Path("/doctors")
	public class DoctorService {
	//A common method to connect to the DB 
		private Connection connect() {
			Connection con = null;
			
			try {
				 Class.forName("com.mysql.jdbc.Driver");
				 //Provide the correct details: DBServer/DBName, username, password 
				 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");

				//For testing          
				 System.out.print("Successfully connected");
				 
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return con; 
		}
		
	@RolesAllowed("DOCTOR")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response getAllDoctors() 
		{
		
			Doctors list = new Doctors();
			list.setDoctorList(new ArrayList<Doctor>());
			try {  
				Connection con = connect();  
				if (con == null)  {   
					return Response.status(Status.INTERNAL_SERVER_ERROR).build();  
				}
	
			  String query = "select * from doctor";   
			  Statement stmt = con.createStatement();   
			  ResultSet rs = stmt.executeQuery(query); 
	
			  // iterate through the rows in the result set   
			  while (rs.next())   {    
				  int Doctor_ID = rs.getInt("Doctor_ID");    
				  String Doctor_Name = rs.getString("Doctor_Name");    
				  String Doctor_Specialization = rs.getString("Doctor_Specialization");    
				  String Doctor_MedicalRegistrationNo = rs.getString("Doctor_MedicalRegistrationNo");    
				  String Doctor_ContactNo = rs.getString("Doctor_ContactNo"); 
				  String Doctor_Address = rs.getString("Doctor_Address");
				  String Doctor_Email = rs.getString("Doctor_Email");
				  String Doctor_NIC = Integer.toString(rs.getInt("Doctor_NIC"));
				  
				  list.getDoctorList().add(new Doctor(Doctor_ID, Doctor_Name,Doctor_Specialization,Doctor_MedicalRegistrationNo,Doctor_ContactNo,Doctor_Address,Doctor_Email,Doctor_NIC));
			  } 
	
			  con.close();
			  
			  return Response.status(Status.OK).entity(list).build();  
			}
			catch (Exception e) { 
				System.err.println(e.getMessage()); 
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();  
			}
		}
		
		@RolesAllowed("DOCTOR")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response addDoctor(Doctor d) throws URISyntaxException 
		{
			 try  {   
				 Connection con = connect(); 
			 
			  if (con == null)   {    
				  return Response.status(Status.INTERNAL_SERVER_ERROR).build();   
			  } 
			  if(d == null){
					return Response.status(400).entity("Please add Doctors details !!").build();
				}
				
				if(d.getName() == null) {
					return Response.status(400).entity("Please provide the Doctor name !!").build();
				}
			  
			
			String query = " insert into doctor (`Doctor_ID`,`Doctor_Name`,`Doctor_Specialization`,`Doctor_MedicalRegistrationNo`,`Doctor_ContactNo`,`Doctor_Address`,`Doctor_Email`,`Doctor_NIC`)"+" values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values 

			//execute the statement   
			preparedStmt.execute();   
			con.close(); 
			
			return Response.created(new URI("/rest/doctors/"+d.getId())).build(); 
				}
				catch (Exception e) { 
					System.err.println(e.getMessage()); 
					return Response.status(Status.INTERNAL_SERVER_ERROR).build();  
				}
		}
		
		@GET
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateDoctorById(@PathParam("id") Integer id) 
		{
			if(id  < 0){
				return Response.noContent().build();
			}
			Doctor doc = new Doctor();
			
			doc.setId(id);
			doc.setName("Lokesh Gupta");
			
			GenericEntity<Doctor> entity = new GenericEntity<Doctor>(doc, Doctor.class);
			return Response.ok().entity(entity).build();
		}

		@PUT
		@Path("/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateDoctorById(@PathParam("id") Integer id, Doctor e) 
		{
			Doctor updatedDoctor = new Doctor();
			
			if(e.getName() == null) {
				return Response.status(400).entity("Please provide the employee name !!").build();
			}
			
			updatedDoctor.setId(id);
			updatedDoctor.setName(e.getName());
			
			return Response.ok().entity(updatedDoctor).build();
		}
		
		@DELETE
		@Path("/{Doctor_ID}")
		public Response deleteDoctorById(@PathParam("Doctor_ID")String Doctor_ID) 
		{		
			 try  {   
				 Connection con = connect(); 
			 
			  if (con == null)   {    
				  return Response.status(Status.INTERNAL_SERVER_ERROR).build();   
			  } 
			  
			  String query = "delete from doctor where Doctor_ID=?"; 
				 
			  PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			  // binding values   
			  preparedStmt.setInt(1, Integer.parseInt(Doctor_ID));       
			  // execute the statement   
			  preparedStmt.execute();   
			  con.close(); 
			  
			  return Response.status(202).entity("Doctors deleted successfully !!").build(); 
				}
				catch (Exception e) { 
					System.err.println(e.getMessage()); 
					return Response.status(Status.INTERNAL_SERVER_ERROR).build();  
				}
		}
		
		
		
	}
