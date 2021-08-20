package com.cadence.cadence_queue_job.resource;

import com.cadence.cadence_queue_job.business.DomainBusiness;
import com.cadence.cadence_queue_job.dto.RegisterDomainDto;
import com.cadence.cadence_queue_job.exception.CadenceQueueException;
import com.cadence.cadence_queue_job.utils.ResponseUtils;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/domain")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DomainResource {
	
	@GET
	@Path("/register/{id}")
	public Response registerDomain(@PathParam("id") String id) {
		DomainBusiness bussines = new DomainBusiness();
		RegisterDomainDto registerDomain;
		try {
			registerDomain = bussines.registerDomain(id);
		} catch (CadenceQueueException e) {			
			return Response.serverError().entity(e.getMessage()).build();
		} catch (IllegalArgumentException e) {
			return ResponseUtils.buildIllegalArgumentResponse(e);
		}
		return ResponseUtils.buildOkResponse(registerDomain);
	}
	
	@GET
	@Path("/register/sample-domains")
	public Response registerThreeSampleDomains() {
		DomainBusiness bussines = new DomainBusiness();
		try
		{
			return ResponseUtils.buildOkResponse(bussines.registerThreeSampleDomains());
		} catch (IllegalArgumentException e) {
			return ResponseUtils.buildIllegalArgumentResponse(e);
		}
	}
	
}
