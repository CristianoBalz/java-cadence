package com.cadence.cadence_queue_job.queue;

import com.cadence.cadence_queue_job.dto.JobSucessInfoDto;
import com.cadence.cadence_queue_job.form.JobInfoForm;
import com.cadence.cadence_queue_job.model.TypeWorkflowExecution;
import com.uber.cadence.workflow.WorkflowMethod;

public interface QueueWorkflow {
	
	@WorkflowMethod(executionStartToCloseTimeoutSeconds = 60)
	JobSucessInfoDto executeJob(JobInfoForm form, String oid, TypeWorkflowExecution type);	
	
}
