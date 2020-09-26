package com.ddlab.rnd.resources;

import java.net.URI;
//import java.util.Map;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
//import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterStyle;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ddlab.rnd.entities.Employee;
import com.ddlab.rnd.service.EmpService;

@Tag(name = "All POST Functionality",description = "APIs for HTTP POST")
@Path(value = "/post")
public class PostController {

	@Autowired
	@Qualifier("emp1")
	private EmpService empService;

	@POST
	@Path("/createEmp")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "Creates an Employee", description = "Creates a new employee")
	@APIResponses({ @APIResponse(responseCode = "201", description = "Employee created successfully"),
			@APIResponse(name = "500", responseCode = "500", description = "Internal service error") })
	public Response createEmployee(
			@Parameter(description = "Employee object as json", required = true) @BeanParam @RequestBody Employee emp) {
		empService.createEmployee(emp);
		URI builder = UriBuilder.fromPath("/ddlab/get").build();
		return Response.created(builder).build();
	}

	@POST
	@Path("/saveEmp")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Operation(summary = "Saves an Employee by passing form parameters", description = "Saves a new employee")
	@APIResponses({ @APIResponse(responseCode = "201", description = "Employee created successfully"),
			@APIResponse(name = "500", responseCode = "500", description = "Internal service error") })
	public Response saveEmployee(
			@Parameter(style = ParameterStyle.FORM, description = "Emp Id", required = true, name = "id", 
			schema = @Schema(implementation = Integer.class)) 
			@FormParam(value = "id") int id,
			@Parameter(style = ParameterStyle.FORM, description = "First Name", required = true, name = "firstName", 
			schema = @Schema(implementation = String.class)) 
			@FormParam(value = "firstName") String firstName,
			@Parameter(style = ParameterStyle.FORM, description = "Last Name", required = true, name = "lastName", 
				schema = @Schema(implementation = String.class)) 
			@FormParam(value = "lastName") String lastName) {
		Employee emp = new Employee(id, firstName, lastName);
		System.out.println("emp = " + emp);
		empService.createEmployee(emp);
		URI builder = UriBuilder.fromPath("/ddlab/get").build();
		return Response.created(builder).build();
	}

	@POST
	@Path("/createEmpKey")
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "Creates an Employee by passing security key as Header", description = "Creates a new employee")
	@APIResponses({ @APIResponse(responseCode = "201", description = "Employee created successfully"),
			@APIResponse(name = "500", responseCode = "500", description = "Internal service error") })
	public Response createEmployeeKey(
			@Parameter(description = "Security as key", required = true) 
			@HeaderParam("key") String Key,
			@Parameter(description = "Employee object as json", required = true) 
			@BeanParam @RequestBody Employee emp) {
		System.out.println("Key = " + Key);
		URI builder = UriBuilder.fromPath("/emp/id/{id}").build(111);
		return Response.created(builder).build();
	}
}
