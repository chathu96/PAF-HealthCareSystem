package com.healthcare.doctorapi.client;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.filter.LoggingFilter;

import com.healthcare.doctorapi.beans.Doctor;
import com.healthcare.doctorapi.beans.Doctors;

public class DoctorClientApp {
	public static void main(String[] args) throws IOException 
	{
		httpGETCollectionExample();
		httpGETEntityExample();
		httpPOSTMethodExample();
		httpPUTMethodExample();
		httpDELETEMethodExample();
	}
	
	private static void httpGETCollectionExample() 
	{
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder()
																    .nonPreemptive()
																    .credentials("user", "password")
																    .build();

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.register(feature) ;

		Client client = ClientBuilder.newClient( clientConfig );
		WebTarget webTarget = client.target("http://localhost:8085/Doctor/rest").path("doctors");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		
		Doctors doctors = response.readEntity(Doctors.class);
		List<Doctor> listOfDoctors = doctors.getDoctorList();
			
		System.out.println(response.getStatus());
		System.out.println(Arrays.toString( listOfDoctors.toArray(new Doctor[listOfDoctors.size()]) ));
	}
	
	private static void httpGETEntityExample() 
	{
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:8085/Doctor/rest").path("doctors").path("1");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.get();
		
		Doctor doctor = response.readEntity(Doctor.class);
			
		System.out.println(response.getStatus());
		System.out.println(doctor);
	}

	private static void httpPOSTMethodExample() 
	{
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:8085/Doctor/rest").path("doctors");
		
		Doctor doc = new Doctor();
		doc.setId(1);
		doc.setName("David Feezor");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.post(Entity.entity(doc, MediaType.APPLICATION_XML));
		
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
	
	private static void httpPUTMethodExample() 
	{
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:8085/Doctor/rest").path("doctors").path("1");
		
		Doctor doc = new Doctor();
		doc.setId(1);
		doc.setName("David Feezor");
		
		Invocation.Builder invocationBuilder =	webTarget.request(MediaType.APPLICATION_XML);
		Response response = invocationBuilder.put(Entity.entity(doc, MediaType.APPLICATION_XML));
		
		Doctor doctor = response.readEntity(Doctor.class);
			
		System.out.println(response.getStatus());
		System.out.println(doctor);
	}
	
	private static void httpDELETEMethodExample() 
	{
		Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
		WebTarget webTarget = client.target("http://localhost:8085/Doctor/rest").path("doctors").path("1");
		
		Invocation.Builder invocationBuilder =	webTarget.request();
		Response response = invocationBuilder.delete();
		
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
}

