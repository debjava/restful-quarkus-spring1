package com.ddlab.rnd.docs;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(tags = 
			{ 
				@Tag(name = "All GET Functionality"),
				@Tag(name = "All POST Functionality"),
				@Tag(name = "All PUT Functionality"),
				@Tag(name = "Delete Functionality"),
				@Tag(name = "All PATCH Functionality"),
				@Tag(name = "File Download Functionality"),
				@Tag(name = "File Upload Functionality"),
				@Tag(name = "Multiple File Upload Functionality")
			}, 
			info = @Info(title = "My API on Quarkus", version = "1.0.1", 
			contact = @Contact(name = "DDLAB Inc API Support", 
			url = "http://exampleurl.com/contact", 
			email = "deba.java@gmail.com"), 
			license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")))
public class MyAPIApplication extends Application {
}