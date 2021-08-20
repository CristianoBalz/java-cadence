package com.cadence.cadence_queue_job.business;

import java.util.HashMap;
import java.util.Map;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;

public class WorkflowClientManager {
	
	private static Map<String, WorkflowClient> clients = new HashMap<>();
	
	private WorkflowClientManager() {}	
	
	public static WorkflowClient getClient(String domain) {
		if(clients.containsKey(domain)) {
			return clients.get(domain);
		}
		WorkflowClient client = createClient(domain);
		clients.put(domain, client);
		return client;
	}
	
	private static WorkflowClient createClient(String domain) {
		return WorkflowClient.newInstance(
				new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
				WorkflowClientOptions.newBuilder().setDomain(domain).build());
	}
}
