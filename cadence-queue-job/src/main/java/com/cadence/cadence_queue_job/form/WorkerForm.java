package com.cadence.cadence_queue_job.form;

public class WorkerForm {
	String domain;
	String taskList;
	
	public WorkerForm() {}
	
	public WorkerForm(String domain, String taskList) {
		this.domain = domain;
		this.taskList = taskList;
	}

	public String getDomain() {
		return domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getTaskList() {
		return taskList;
	}
	
	public void setTaskList(String taskList) {
		this.taskList = taskList;
	}

	@Override
	public String toString() {
		return "WorkerForm [domain=" + domain + ", taskList=" + taskList + "]";
	}
	
	
}
