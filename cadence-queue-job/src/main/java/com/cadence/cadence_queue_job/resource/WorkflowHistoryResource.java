package com.cadence.cadence_queue_job.resource;

import com.cadence.cadence_queue_job.business.WorkflowHistoryBusiness;
import com.cadence.cadence_queue_job.form.HistoryFilterForm;
import com.cadence.cadence_queue_job.form.WorkflowIdentityForm;
import com.cadence.cadence_queue_job.utils.ResponseUtils;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/workflow/history")
@Consumes(MediaType.APPLICATION_JSON)
public class WorkflowHistoryResource {
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWorkflowHistory(WorkflowIdentityForm form) {
		WorkflowHistoryBusiness business = new WorkflowHistoryBusiness();
		try
		{
			String workflowExecutionHistory = business.getWorkflowExecutionHistory(form);
			return Response.ok().entity(workflowExecutionHistory).build();
		} catch (IllegalArgumentException e) {
			return ResponseUtils.buildIllegalArgumentResponse(e);
		}
	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getAllHistoryJob() {
		WorkflowHistoryBusiness business = new WorkflowHistoryBusiness();
		return ResponseUtils.buildOkResponse(business.getAllHistoryJob());		
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/clear")
	public Response clearHistoryJob() {
		WorkflowHistoryBusiness business = new WorkflowHistoryBusiness();
		business.clearHistoryJob();
		return Response.ok().entity("OK").build();
	}
		
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/filter")
	public Response getAllHistoryJobByFilter(HistoryFilterForm form) {
		try
		{
			WorkflowHistoryBusiness business = new WorkflowHistoryBusiness();
			return ResponseUtils.buildOkResponse(business.getAllHistoryByFilter(form));
		} catch (IllegalArgumentException e) {
			return ResponseUtils.buildIllegalArgumentResponse(e);
		}		
	}
	
}
