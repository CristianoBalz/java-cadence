package com.cadence.cadence_queue_job.form;

public class WorkflowIdentityForm {
	String domain;
	String workflowId;
	String runId;
	
	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getWorkflowId() {
		return workflowId;
	}
	
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	
	public String getRunId() {
		return runId;
	}
	
	public void setRunId(String runId) {
		this.runId = runId;
	}

	@Override
	public String toString() {
		return "WorkflowIdentity [domain=" + domain + ", workflowId=" + workflowId + ", runId=" + runId + "]";
	}
		
}
