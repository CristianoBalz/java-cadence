package com.cadence.cadence_queue_job.dto;

public class JobSucessInfoDto {
	String jobId;
	String domain;
	String message;
	
	public JobSucessInfoDto(String jobId, String domain, String message) {
		this.jobId = jobId;
		this.domain = domain;
		this.message = message;
	}

	public String getJobId() {
		return jobId;
	}

	public String getDomain() {
		return domain;
	}

	public String getMessage() {
		return message;
	}	

	@Override
	public String toString() {
		return "JobSucessInfoDto [jobId=" + jobId + ", domain=" + domain + ", message=" + message + "]";
	}	
	
}
