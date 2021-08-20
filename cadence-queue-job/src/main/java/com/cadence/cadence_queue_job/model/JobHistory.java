package com.cadence.cadence_queue_job.model;

import java.util.Date;

public class JobHistory {
	private String domain;
	private String taskList;
	private String jobId;
	private Date process;
	private boolean sucess;
	private TypeWorkflowExecution type;
	private String oid;
	private String message;
	
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
	
	public Date getProcess() {
		return process;
	}
	
	public void setProcess(Date process) {
		this.process = process;
	}
	
	public boolean isSucess() {
		return sucess;
	}
	
	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}

	public TypeWorkflowExecution getType() {
		return type;
	}

	public void setType(TypeWorkflowExecution type) {
		this.type = type;
	}	

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}	

	public String getTaskList() {
		return taskList;
	}

	public void setTaskList(String taskList) {
		this.taskList = taskList;
	}	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "JobHistory [domain=" + domain + ", taskList=" + taskList + ", jobId=" + jobId + ", process=" + process
				+ ", sucess=" + sucess + ", type=" + type + ", oid=" + oid + ", message=" + message + "]";
	}
	
}
