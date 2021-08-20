package com.cadence;

import com.cadence.GettingStarted.HelloWorldImpl;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;

public class Application
{
	private static final String DOMAIN = "test-domain";
	private static final String TASK_LIST = "HelloWorldTaskList";

	public static void main( String[] args )
	{		
		 WorkflowClient workflowClient =
			      WorkflowClient.newInstance(
			          new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
			          WorkflowClientOptions.newBuilder().setDomain(DOMAIN).build());
			  // Get worker to poll the task list.
			  WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
			  Worker worker = factory.newWorker(TASK_LIST);
			  worker.registerWorkflowImplementationTypes(HelloWorldImpl.class);
			  factory.start();
			  System.out.println("This worker it's running!!!");	
}
}