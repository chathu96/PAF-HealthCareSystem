package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

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
	public String insertpayment(@FormParam("patientID") String patientID, @FormParam("docID") String docID,
			@FormParam("card_no") String card_no, @FormParam("cvv") String cvv,
			@FormParam("card_type") String card_type, @FormParam("exp_date") String exp_date,
			@FormParam("amount") String amount) {
		String output = payObj.insertpayment(patientID, docID, card_no, cvv, card_type, exp_date, amount);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(paymentData).getAsJsonObject();
		// Read the values from the JSON object
		String payID = itemObject.get("payID").getAsString();
		String patientID = itemObject.get("patientID").getAsString();
		String docID = itemObject.get("docID").getAsString();
		String card_no = itemObject.get("card_no").getAsString();
		String cvv = itemObject.get("cvv").getAsString();
		String card_type = itemObject.get("card_type").getAsString();
		String exp_date = itemObject.get("exp_date").getAsString();
		String amount = itemObject.get("amount").getAsString();
		String output = payObj.updatepayment(payID,patientID,docID,card_no,cvv,card_type,exp_date,amount);
		return output;
	}
}
