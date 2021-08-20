package com.cadence.cadence_queue_job.queue;

import java.util.Arrays;

public enum TaskListQueueEnums {
	JOB_QUEUE("job-queue"),
	MAIL_QUEUE("mail-queue");
	
	String taskList;
	
	TaskListQueueEnums(String taskList) {
		this.taskList = taskList;
	}
	
	public static TaskListQueueEnums fromString(String value) {
		try {
			return valueOf(value);
        } catch (Exception e) {            
        	throw new IllegalArgumentException(value + " is invalid value. Supported values are " + Arrays.asList(values()));
        }
	}

	public String getTaskList() {
		return taskList;
	}
	
	
}
