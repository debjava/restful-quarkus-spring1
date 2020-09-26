package com.ddlab.rnd.entities;

import java.io.InputStream;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
	
	// https://stackoverflow.com/questions/61386439/unable-to-find-a-messagebodyreader-error-with-multipart-request-containing-a-f
	@Schema(type = SchemaType.STRING, format = "text", description = "Paste the json string of employee information")
	@FormParam("empInfo")
	@PartType(MediaType.TEXT_PLAIN)
	private String empInfo;

	@Schema(type = SchemaType.STRING, format = "binary", description = "Upload either .docx or .pdf format document")
	@FormParam("resume")
	@PartType(MediaType.APPLICATION_OCTET_STREAM)
	private InputStream resume;
	
}
