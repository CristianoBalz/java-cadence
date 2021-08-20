package com.cadence.cadence_queue_job.resource;

import com.cadence.cadence_queue_job.business.WorkflowBusiness;
import com.cadence.cadence_queue_job.exception.CadenceQueueException;
import com.cadence.cadence_queue_job.form.JobInfoForm;
import com.cadence.cadence_queue_job.utils.ResponseUtils;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/workflow")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WorkflowResource {

	@POST
	@Path("/start-job")
	public Response startJob(JobInfoForm form) {
		WorkflowBusiness business = new WorkflowBusiness();
		try {
			return ResponseUtils.buildOkResponse(business.startJob(form));
		} catch (CadenceQueueException e) {			
			return Response.serverError().entity(e.getMessage()).build();
		} catch (IllegalArgumentException e) {
			return ResponseUtils.buildIllegalArgumentResponse(e);
		}
	}

	@POST
	@Path("/start-assync-job")
	public Response startAssyncJob(JobInfoForm form) {
		WorkflowBusiness business = new WorkflowBusiness();
		try {
			return ResponseUtils.buildOkResponse(business.startAssyncJob(form));
		} catch (CadenceQueueException e) {			
			return Response.serverError().entity(e.getMessage()).build();
		} catch (IllegalArgumentException e) {
			return ResponseUtils.buildIllegalArgumentResponse(e);
		}
	}
	
	@GET
	@Path("/start-assync-sample-jobs")
	public Response startAssyncSamplesJob() {
		WorkflowBusiness business = new WorkflowBusiness();
		try {
			return ResponseUtils.buildOkResponse(business.startAssyncSamplesJob());
		} catch (CadenceQueueException e) {			
			return Response.serverError().entity(e.getMessage()).build();
		} catch (IllegalArgumentException e) {
			return ResponseUtils.buildIllegalArgumentResponse(e);
		}
	}

}
