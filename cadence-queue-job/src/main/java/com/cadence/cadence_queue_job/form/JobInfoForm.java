package com.cadence.cadence_queue_job.form;

public class JobInfoForm {
	String domain;
	String jobId;
	String taskList;
	
	public JobInfoForm() {}
	
	public JobInfoForm(String domain, String jobId, String taskList) {
		this.domain = domain;
		this.jobId = jobId;
		this.taskList = taskList;
	}

	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getJobId() {
		return jobId;
	}
	
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}	

	public String getTaskList() {
		return taskList;
	}

	public void setTaskList(String taskList) {
		this.taskList = taskList;
	}

	@Override
	public String toString() {
		return "JobInfoForm [domain=" + domain + ", jobId=" + jobId + ", taskList=" + taskList + "]";
	}	
	
}
