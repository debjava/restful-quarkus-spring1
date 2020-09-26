package com.ddlab.rnd.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Encoding;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.ddlab.rnd.entities.Employee;
import com.ddlab.rnd.entities.MultipartBody;
import com.ddlab.rnd.entities.UserProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Tag(name = "File Upload Functionality",description = "APIs to upload file")
@Path("/upload")
public class FileUploadController {

	private static String UPLOAD_DIR = "E:/sure-delete";

	// Unable to generate swagger UI
	@Operation(summary = "Upload a single file", description = "Upload a single file")
	@APIResponses({
			@APIResponse(responseCode = "200", description = "Upload file successfully"),
			@APIResponse(name = "500", responseCode = "500", description = "Internal service error") })
	@RequestBody(content = @Content(
			mediaType = MediaType.MULTIPART_FORM_DATA,
			schema = @Schema(type = SchemaType.STRING, format = "binary"),
			encoding = @Encoding(name = "attachment", contentType = "application/octet-stream")))
	@POST
	@Path("/singleFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response handleFileUpload(@MultipartForm MultipartFormDataInput input) {
		String fileName = null;

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		// Get file data to save
		List<InputPart> inputParts = uploadForm.get("attachment");
		for (InputPart inputPart : inputParts) {
			try {
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);
				InputStream inputStream = inputPart.getBody(InputStream.class, null);
				byte[] bytes = IOUtils.toByteArray(inputStream);
				File customDir = new File(UPLOAD_DIR);
				if (!customDir.exists()) {
					customDir.mkdir();
				}
				fileName = customDir.getCanonicalPath() + File.separator + fileName;
				Files.write(Paths.get(fileName), bytes, StandardOpenOption.CREATE);
				return Response.status(200).entity("Uploaded file name : " + fileName).build();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Response.status(200).entity("Uploaded file name : " + fileName).build();
	}

	private String getFileName(MultivaluedMap<String, String> header) {
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String[] name = filename.split("=");
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	
	@Operation(summary = "Uploads a single file to the server (Working Annotation)",description = "Annotation Working properly")
	@RequestBody(content = @Content(
			mediaType = MediaType.MULTIPART_FORM_DATA,
			schema = @Schema(implementation = MultipartBody.class),
			encoding = @Encoding(name = "file", contentType = "application/octet-stream")))
	@POST
	@Path("/singleFile1") // IN another way
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response handleFileUploadForm(@MultipartForm MultipartBody bodyInput) {
		InputStream inputStream = bodyInput.getFile();
		String fileName = bodyInput.getFileName();
		byte[] bytes = null;
		try {
			File customDir = new File(UPLOAD_DIR);
			bytes = IOUtils.toByteArray(inputStream);
			fileName = customDir.getCanonicalPath() + File.separator + fileName;
			Files.write(Paths.get(fileName), bytes, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity("Uploaded file name : " + fileName).build();
	}

	@Operation(summary = "Uploads a profile/resume to the portal. (Working Annotation)",description = "Annotation Working properly")
	@RequestBody(content = @Content(
			mediaType = MediaType.MULTIPART_FORM_DATA,
			schema = @Schema(implementation = UserProfile.class),
			encoding = @Encoding(name = "file", contentType = "application/octet-stream")))
	@POST
	@Path("/uploadProfile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadProfile(@MultipartForm UserProfile bodyInput) {
		String empString = bodyInput.getEmpInfo();
		ObjectMapper objectMapper = new ObjectMapper();
		Employee emp = null;
		try {
			emp = objectMapper.readValue(empString, Employee.class);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		System.out.println("Employee in String: " + emp);
		InputStream inputStream = bodyInput.getResume();
		String fileName = "user-profile";
		byte[] bytes = null;
		try {
			File customDir = new File(UPLOAD_DIR);
			bytes = IOUtils.toByteArray(inputStream);
			fileName = customDir.getCanonicalPath() + File.separator + fileName + ".docx";
			Files.write(Paths.get(fileName), bytes, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity("User profile uploaded successfully .").build();
	}

	
	@RequestBody(
	        content = @Content(
	            mediaType = "multipart/form-data",
	            schema = @Schema(type = SchemaType.STRING, format = "binary"),
	            encoding = @Encoding(
	                name = "file",
	                contentType = "application/pdf, image/png"
	            )
	        ))
	@Operation(summary = "Uploads product support")
	@POST
	@Path("/uploadSupport")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadSupport(@MultipartForm MultipartFormDataInput multipart) throws IOException {
		Map<String, List<InputPart>> uploadForm = multipart.getFormDataMap();
		List<String> fileNames = new ArrayList<>();

		List<InputPart> inputParts = uploadForm.get("file");
		String fileName = null;
		for (InputPart inputPart : inputParts) {
			try {
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);
				fileNames.add(fileName);
				System.out.println("File Name: " + fileName);
				InputStream inputStream = inputPart.getBody(InputStream.class, null);
				byte[] bytes = IOUtils.toByteArray(inputStream);
				File customDir = new File(UPLOAD_DIR);
				fileName = customDir.getAbsolutePath() + File.separator + fileName;
				Files.write(Paths.get(fileName), bytes, StandardOpenOption.CREATE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String empString = multipart.getFormDataPart("empInfo", String.class, null);
		System.out.println("Emp String: " + empString);
		ObjectMapper objectMapper = new ObjectMapper();
		Employee emp = null;
		try {
			emp = objectMapper.readValue(empString, Employee.class);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		System.out.println("Employee in String: " + emp);

		String issue = multipart.getFormDataPart("issue", String.class, null);
		System.out.println("Issue: " + issue);
		return Response.ok().entity("Your support request has been logged.").build();
	}

}
