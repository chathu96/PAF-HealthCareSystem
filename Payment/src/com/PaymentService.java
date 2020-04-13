package com;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/payment")
public class PaymentService {
	@GET 
    @Produces("text/plain")
    public String getIt() {
        return "Hi Salman!";
    }
}
