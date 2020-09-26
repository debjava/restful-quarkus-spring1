package com.ddlab.rnd.resources;

import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import com.ddlab.rnd.entities.Employee;
import com.ddlab.rnd.service.EmpService;

@Tag(name = "All PATCH Functionality",description = "APIs for HTTP PATCH")
@Path("/patch")
public class PatchController {

	@Autowired
	private EmpService empService;

	@Operation(summary = "Update partial information by patch", description = "Update partial information by patch")
	@APIResponses({
			@APIResponse(responseCode = "200", description = "Updates partial information by patch"),
			@APIResponse(name = "500", responseCode = "500", description = "Internal service error") })
	@PATCH
	@Path("/updatePartialEmp")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEmployee(@Parameter(description = "Employee Id as query param", required = true)
									@QueryParam(value = "id") String id,
									@Parameter(description = "Login name as query param", required = true)
									@QueryParam("loginName") String loginName) {
		// First find the emp by id
		Employee empById = empService.getEmployeeById(id);
		empById.setFirstName(loginName);
		Employee updatedEmp = empService.updateEmployeeLogin(empById);
		System.out.println("updatedEmp = " + updatedEmp);
		return Response.ok().entity(updatedEmp).build();
	}
}
