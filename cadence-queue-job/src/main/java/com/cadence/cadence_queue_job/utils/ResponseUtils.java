package com.cadence.cadence_queue_job.utils;

import jakarta.ws.rs.core.Response;

public class ResponseUtils {
	private ResponseUtils() {}
	
	public static Response buildOkResponse(Object obj) {
		String json = JsonUtils.toJson(obj);
		return Response.ok(json).build();
	}	
	
	public static Response buildIllegalArgumentResponse(IllegalArgumentException e) {
		return Response.status(404).entity(e.getMessage()).build();
	}
}