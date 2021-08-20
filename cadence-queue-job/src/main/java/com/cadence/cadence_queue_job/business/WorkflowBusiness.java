package com.cadence.cadence_queue_job.business;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.cadence.cadence_queue_job.dto.JobSucessInfoDto;
import com.cadence.cadence_queue_job.dto.WorkflowExecutedInfoDto;
import com.cadence.cadence_queue_job.exception.CadenceQueueException;
import com.cadence.cadence_queue_job.form.JobInfoForm;
import com.cadence.cadence_queue_job.model.TypeWorkflowExecution;
import com.cadence.cadence_queue_job.queue.QueueWorkflow;
import com.cadence.cadence_queue_job.queue.TaskListQueueEnums;
import com.cadence.cadence_queue_job.utils.StringUtils;
import com.google.common.base.Throwables;
import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowException;
import com.uber.cadence.client.WorkflowOptions;

public class WorkflowBusiness {

	public JobSucessInfoDto startJob(JobInfoForm form) throws CadenceQueueException {
		validateForm(form);
		TaskListQueueEnums taskListEnum = TaskListQueueEnums.fromString(form.getTaskList());
		
		WorkflowClient workflowClient = WorkflowClientManager.getClient(form.getDomain());

		QueueWorkflow workflow = workflowClient.newWorkflowStub(QueueWorkflow.class,
				new WorkflowOptions.Builder().setTaskList(taskListEnum.getTaskList()).build());

		String oid = UUID.randomUUID().toString();

		try {
			return workflow.executeJob(form, oid, TypeWorkflowExecution.SYNCR);
		} catch (WorkflowException e) {
			Throwable cause = Throwables.getRootCause(e);
			System.out.println(cause.getMessage());
			System.out.println("\nStack Trace:\n" + Throwables.getStackTraceAsString(e));
			throw new CadenceQueueException("Fail to run workflow from oid \"" + oid + "\".", e);
		}
	}

	public WorkflowExecutedInfoDto startAssyncJob(JobInfoForm form) throws CadenceQueueException {
		validateForm(form);

		TaskListQueueEnums taskListEnum = TaskListQueueEnums.fromString(form.getTaskList());

		WorkflowClient workflowClient = WorkflowClientManager.getClient(form.getDomain());

		QueueWorkflow workflow = workflowClient.newWorkflowStub(QueueWorkflow.class,
				new WorkflowOptions.Builder().setTaskList(taskListEnum.getTaskList()).build());

		String oid = UUID.randomUUID().toString();
		try {
			WorkflowExecution workflowExec = WorkflowClient.start(workflow::executeJob, form, oid,
					TypeWorkflowExecution.ASSYNC);

			return new WorkflowExecutedInfoDto(oid, form.getDomain(), workflowExec.getWorkflowId(),
					workflowExec.getRunId(), form.getTaskList(), form.getJobId());
		} catch (WorkflowException e) {
			Throwable cause = Throwables.getRootCause(e);
			System.out.println(cause.getMessage());
			System.out.println("\nStack Trace:\n" + Throwables.getStackTraceAsString(e));
			throw new CadenceQueueException("Fail to run workflow from oid \"" + oid + "\".", e);
		}
	}
	
	public List<WorkflowExecutedInfoDto> startAssyncSamplesJob() throws CadenceQueueException {
		List<JobInfoForm> form = new ArrayList<>();
		List<String> sampleDomains = new DomainBusiness().getSampleDomains();	
				
		for(String domain : sampleDomains) {
			for(TaskListQueueEnums queues : TaskListQueueEnums.values()) {
				form.addAll(IntStream.range(1, 51).boxed()
						.map(i -> new JobInfoForm(domain,"Job "+i,queues.name()))
						.collect(Collectors.toList()));
			}
		}
		
		List<WorkflowExecutedInfoDto> returnList = new ArrayList<>();
		for(JobInfoForm jobInfo : form) {
			returnList.add(startAssyncJob(jobInfo));
		}
		
		return returnList;
	}	

	private void validateForm(JobInfoForm form) {
		if (form == null) {
			throw new IllegalArgumentException("No data entered");
		}

		if (StringUtils.isNullOrEmpty(form.getDomain())) {
			throw new IllegalArgumentException("Attribute \"domain\" was not informed!");
		}

		if (StringUtils.isNullOrEmpty(form.getJobId())) {
			throw new IllegalArgumentException("Attribute \"jobId\" was not informed!");
		}

		if (StringUtils.isNullOrEmpty(form.getTaskList())) {
			throw new IllegalArgumentException("Attribute \"taskList\" was not informed!");
		}
	}	

}
