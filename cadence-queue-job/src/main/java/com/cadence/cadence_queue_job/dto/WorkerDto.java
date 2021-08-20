package com.cadence.cadence_queue_job.dto;

import java.util.Date;

import com.cadence.cadence_queue_job.queue.TaskListQueueEnums;

public class WorkerDto {
	
	String domain;
	TaskListQueueEnums taskList;
	String hostSpecifiTaskList;
	Date started;	
		
	public WorkerDto(String domain, TaskListQueueEnums taskList, String hostSpecifiTaskList) {		
		this(domain, taskList, hostSpecifiTaskList, new Date());
	}

	public WorkerDto(String domain, TaskListQueueEnums taskList, String hostSpecifiTaskList, Date started) {
		this.domain = domain;
		this.taskList = taskList;
		this.hostSpecifiTaskList = hostSpecifiTaskList;
		this.started = started;
	}

	public String getDomain() {
		return domain;
	}	
	
	public TaskListQueueEnums getTaskList() {
		return taskList;
	}
		
	public String getHostSpecifiTaskList() {
		return hostSpecifiTaskList;
	}	
	
	public Date getStarted() {
		return started;
	}	

	@Override
	public String toString() {
		return "WorkerDto [domain=" + domain + ", taskList=" + taskList + ", hostSpecifiTaskList=" + hostSpecifiTaskList
				+ ", started=" + started + "]";
	}	
	
}
