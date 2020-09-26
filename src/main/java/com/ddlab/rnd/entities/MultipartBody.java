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

@Schema(name="MultipartBody", description="Model for the MultipartBody object")
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultipartBody {

	@Schema(type = SchemaType.STRING, format = "binary")
	@FormParam("file")
	@PartType(MediaType.APPLICATION_OCTET_STREAM)
	private InputStream file;

	@Schema(type = SchemaType.STRING, format = "text")
	@FormParam("fileName")
	@PartType(MediaType.TEXT_PLAIN)
	private String fileName;

}