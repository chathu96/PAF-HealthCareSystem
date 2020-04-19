package com.healthcare.doctorapi;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.healthcare.doctorapi.provider.AuthenticationFilter;
import com.healthcare.doctorapi.provider.GsonMessageBodyHandler;


public class CustomApplication extends ResourceConfig {

	public CustomApplication() {
		packages("com.healthcare.doctorapi");
		register(LoggingFilter.class);
		register(GsonMessageBodyHandler.class);
		register(AuthenticationFilter.class);
	}

}
