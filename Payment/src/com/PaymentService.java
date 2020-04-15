package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Payment;

@Path("/payment")
public class PaymentService {

	Payment payObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {

		return payObj.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertpayment(
			@FormParam("patientID") String patientID,
			@FormParam("docID") String docID,
			@FormParam("card_no") String card_no,
			@FormParam("cvv") String cvv,
			@FormParam("card_type") String card_type,
			@FormParam("exp_date") String exp_date,
			@FormParam("amount") String amount) {
		String output = payObj.insertpayment(patientID, docID, card_no, cvv, card_type, exp_date, amount);
		return output;
	}

}
