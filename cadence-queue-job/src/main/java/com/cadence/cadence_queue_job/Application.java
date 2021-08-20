package com.cadence.cadence_queue_job;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.cadence.cadence_queue_job.model.JobHistory;
import com.cadence.cadence_queue_job.resource.DomainResource;
import com.cadence.cadence_queue_job.resource.WorkerResource;
import com.cadence.cadence_queue_job.resource.WorkflowHistoryResource;
import com.cadence.cadence_queue_job.resource.WorkflowResource;

public class Application {
	
	public static Object monitor = new Object(); 
	public static final URI BASE_URI = URI.create("http://localhost:8080/cadence-queue-job/rest");

	public static List<JobHistory> jobHistory = Collections.synchronizedList(new ArrayList<JobHistory>());
	
	public static HttpServer startHttpServer() {
		final ResourceConfig config = new ResourceConfig();
		config.register(DomainResource.class);
		config.register(WorkerResource.class);
		config.register(WorkflowHistoryResource.class);
		config.register(WorkflowResource.class);			
		
		return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, config);
	}

	public static void main(String[] args) {
		try {
			final HttpServer server = startHttpServer();
			server.start();
			Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
			System.out.println(String.format("Application started.%nStop the application using CTRL+C"));
			Thread.currentThread().join();
		} catch (InterruptedException | IOException ex) {
			Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
