package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

import model.Payment;

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

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
	public String updatepayment(String paymentData) {
		// Convert the input string to a JSON object
		JsonObject payObject = new JsonParser().parse(paymentData).getAsJsonObject();
		// Read the values from the JSON object
		String payID = payObject.get("payID").getAsString();
		String patientID = payObject.get("patientID").getAsString();
		String docID = payObject.get("docID").getAsString();
		String card_no = payObject.get("card_no").getAsString();
		String cvv = payObject.get("cvv").getAsString();
		String card_type = payObject.get("card_type").getAsString();
		String exp_date = payObject.get("exp_date").getAsString();
		String amount = payObject.get("amount").getAsString();
		String output = payObj.updatepayment(payID,patientID,docID,card_no,cvv,card_type,exp_date,amount);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String payData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(payData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String payID = doc.select("payID").text();
	 String output = payObj.deletePayment(payID);
	return output;
	}
}
