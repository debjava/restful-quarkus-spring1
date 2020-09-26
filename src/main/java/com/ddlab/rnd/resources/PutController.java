package com.ddlab.rnd.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import com.ddlab.rnd.entities.Employee;
import com.ddlab.rnd.service.EmpService;

@Tag(name = "All PUT Functionality",description = "APIs for HTTP PUT")
@Path(value = "/put")
public class PutController {

	@Autowired
	private EmpService empService;

	@Operation(summary = "Updates employee information", description = "Updates employee information")
	@APIResponses({
			@APIResponse(responseCode = "200", description = "Gets successful employee information"),
			@APIResponse(name = "500", responseCode = "500", description = "Internal service error") })
	@PUT
	@Path("/updateEmp")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee(Employee emp) {
		Employee updatedEmp = empService.updateEmployeeInfo(emp);
		return Response.ok().entity(updatedEmp).build();
	}
}
