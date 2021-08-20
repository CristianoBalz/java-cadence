package com.cadence.cadence_queue_job.resource;

import com.cadence.cadence_queue_job.business.WorkerBusiness;
import com.cadence.cadence_queue_job.form.WorkerForm;
import com.cadence.cadence_queue_job.utils.ResponseUtils;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/worker")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WorkerResource {

	@POST
	@Path("/start")
	public Response start(WorkerForm form) {
		WorkerBusiness business = new WorkerBusiness();
		try {
			return ResponseUtils.buildOkResponse(business.startWorker(form));
		} catch (IllegalArgumentException e) {
			return ResponseUtils.buildIllegalArgumentResponse(e);
		}
	}

	@GET
	@Path("/start/sample-domains-worker")
	public Response startSampleDomaisWorker() {
		WorkerBusiness business = new WorkerBusiness();
		return ResponseUtils.buildOkResponse(business.startSampleDomaisWorker());
	}
}
