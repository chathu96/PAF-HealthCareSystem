package com;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Payment;

@Path("/payment")
public class PaymentService {
	
	Payment payObj= new Payment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment()
	 {
    
	 return payObj.readPayment();
	 }

}
