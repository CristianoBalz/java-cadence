package com.cadence.cadence_queue_job.business;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.stream.Collectors;

import com.cadence.cadence_queue_job.dto.WorkerDto;
import com.cadence.cadence_queue_job.form.WorkerForm;
import com.cadence.cadence_queue_job.queue.QueueActivityImpl;
import com.cadence.cadence_queue_job.queue.QueueWorkflowImpl;
import com.cadence.cadence_queue_job.queue.TaskListQueueEnums;
import com.cadence.cadence_queue_job.utils.StringUtils;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import com.uber.cadence.worker.WorkerFactoryOptions;
import com.uber.cadence.worker.WorkerOptions;

public class WorkerBusiness {
	
	public WorkerDto startWorker(WorkerForm form) {
		if(StringUtils.isNullOrEmpty(form.getDomain())) {
			throw new IllegalArgumentException("Attribute \"domain\" was not informed!");
		}
		
		if(StringUtils.isNullOrEmpty(form.getTaskList())) {
			throw new IllegalArgumentException("Attribute \"taskList\" was not informed!");
		}
		
		TaskListQueueEnums taskListEnum = TaskListQueueEnums.fromString(form.getTaskList());
		
		String hostSpecifiTaskList = ManagementFactory.getRuntimeMXBean().getName();

	    // Get a new client
	    WorkflowClient workflowClient = WorkflowClientManager.getClient(form.getDomain());
	    
	    // Get worker to poll the common task list.
	    WorkerFactory factory = WorkerFactory.newInstance(workflowClient,
	    		WorkerFactoryOptions.newBuilder()
                	.setMaxWorkflowThreadCount(1000)
                	.setStickyCacheSize(100)
                	.setDisableStickyExecution(false)
                	.build());
	    
	    final Worker workerForCommonTaskList = factory.newWorker(taskListEnum.getTaskList(),
	    		WorkerOptions.newBuilder()
                	.setMaxConcurrentActivityExecutionSize(100)
                	.setMaxConcurrentWorkflowExecutionSize(100)
                	.build());
	    
	    // Workflows are stateful. So you need a type to create instances.
	    workerForCommonTaskList.registerWorkflowImplementationTypes(QueueWorkflowImpl.class);
	    
	    // Activities are stateless and thread safe. So a shared instance is used.	    
	    QueueActivityImpl queueActivityImpl = new QueueActivityImpl();
		workerForCommonTaskList.registerActivitiesImplementations(queueActivityImpl);    
	    
//	    // Get worker to poll the host-specific task list.	    
	    final Worker workerForHostSpecificTaskList = factory.newWorker(hostSpecifiTaskList);
	    workerForHostSpecificTaskList.registerActivitiesImplementations(queueActivityImpl);

	    // Start all workers created by this factory.
	    // Start listening to the workflow and activity task lists.
	    factory.start();
	    
	    return new WorkerDto(form.getDomain(), TaskListQueueEnums.fromString(form.getTaskList()), hostSpecifiTaskList);
	}
	
	public List<WorkerDto> startSampleDomaisWorker() {
		List<String> sampleDomains = new DomainBusiness().getSampleDomains();
		
		List<WorkerForm> listJobQueueTask = sampleDomains.stream()			
		.map(domain -> new WorkerForm(domain, TaskListQueueEnums.JOB_QUEUE.name()))
		.collect(Collectors.toList());
		
		List<WorkerForm> listMailQueueTask = sampleDomains.stream()			
				.map(domain -> new WorkerForm(domain, TaskListQueueEnums.MAIL_QUEUE.name()))
				.collect(Collectors.toList());
		
		listJobQueueTask.addAll(listMailQueueTask);
		
		return listJobQueueTask.stream()
				.map(this::startWorker)
				.collect(Collectors.toList());
	}

}
