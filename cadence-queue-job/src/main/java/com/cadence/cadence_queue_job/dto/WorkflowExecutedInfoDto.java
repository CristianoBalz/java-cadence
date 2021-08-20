package com.cadence.cadence_queue_job.dto;

public class WorkflowExecutedInfoDto {
	private String oid;
	private String domain;
	private String workflowId;
	private String runId;
	private String taskList;
	private String jobId;

	public WorkflowExecutedInfoDto(String oid, String domain, String workflowId, String runId, 
			String taskList, String jobId) {
		this.oid = oid;
		this.domain = domain;
		this.workflowId = workflowId;
		this.runId = runId;
		this.taskList = taskList;
	}

	public String getOid() {
		return oid;
	}
	
	public String getDomain() {
		return domain;
	}
	
	public String getWorkflowId() {
		return workflowId;
	}
	
	public String getRunId() {
		return runId;
	}	

	public String getTaskList() {
		return taskList;
	}	

	public String getJobId() {
		return jobId;
	}

	@Override
	public String toString() {
		return "WorkflowExecutedInfoDto [oid=" + oid + ", domain=" + domain + ", workflowId=" + workflowId + ", runId="
				+ runId + ", taskList=" + taskList + ", jobId=" + jobId + "]";
	}
	
}
