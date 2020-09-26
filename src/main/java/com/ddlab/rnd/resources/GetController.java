package com.ddlab.rnd.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ddlab.rnd.entities.Employee;
import com.ddlab.rnd.service.EmpService;

@Tag(name = "All GET Functionality",description = "APIs for HTTP GET")
@Path("/get")
public class GetController {

	@Autowired
	@Qualifier("emp1")
	private EmpService empService;

	@Operation(summary = "Get all employee information", description = "Get all employee information")
	@APIResponses({ @APIResponse(responseCode = "200", description = "Gets all employee information"),
			@APIResponse(name = "500", responseCode = "500", description = "Internal service error") })
	@GET
	@Path("/allEmps")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEmployees() {
		List<Employee> allEmps = empService.getAllEmployees();
		return Response.ok().entity(allEmps).build();
	}

	@Operation(summary = "Handles exception", description = "Handles exception while retrieving employee information")
	@APIResponses({ @APIResponse(responseCode = "200", description = "Gets all employee information with exception"),
			@APIResponse(name = "500", responseCode = "500", description = "Internal service error") })
	@GET
	@Path("/allEmpException1")
	public Response getAllEmpsWithException1() {
		List<Employee> allEmps = empService.getAllEmpsException1();
		return Response.ok().entity(allEmps).build();
	}

	@Operation(summary = "Gets employee information by emp id", description = "Gets employee information by emp id")
	@APIResponses({ @APIResponse(responseCode = "200", description = "Gets successful employee information by emp id"),
			@APIResponse(name = "500", responseCode = "500", description = "Internal service error") })
	@GET
	@Path("/emp/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeePerId(
			@Parameter(description = "Employee id as String", required = true) 
			@PathParam(value = "id") String id) {
		Employee empById = empService.getEmployeeById(id);
		return Response.ok().entity(empById).build();
	}

	@Operation(summary = "Gets employee information by emp name", description = "Gets employee information by emp name")
	@APIResponses({
			@APIResponse(responseCode = "200", description = "Gets successful employee information by emp name"),
			@APIResponse(name = "500", responseCode = "500", description = "Internal service error") })
	@GET
	@Path("/emp/name")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeByName(
			@Parameter(description = "Employee first name as query parameter", required = true) 
			@QueryParam(value = "firstName") String firstName,
			@Parameter(description = "Employee last name as query parameter", required = true) 
			@QueryParam(value = "lastName") String lastName) {
		Employee empById = empService.searchEmpByName(firstName, lastName);
		// localhost:8090/myapp/get/emp/name?firstName=DD&lastName=Mishra
		return Response.ok().entity(empById).build();
	}
}
