package com.ddlab.rnd.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import com.ddlab.rnd.service.EmpService;

@Tag(name = "Delete Functionality",description = "APIs to delete")
@Path("/delete")
public class DeleteController {

	@Autowired
	private EmpService empService;

	@Operation(summary = "Delete employee information by emp id", description = "Deletes employee information")
	@APIResponses({
			@APIResponse(responseCode = "204", description = "Employee information deleted, but no actual content"),
			@APIResponse(name = "500", responseCode = "500", description = "Internal service error") })
	@DELETE
	@Path("emp/{id}")
	public Response deleteArticle(
			@Parameter(description = "Employee id as path", required = true) @PathParam("id") Integer id) {
		empService.deleteEmployee(id);
		return Response.noContent().build();
	}
}
