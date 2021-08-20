package com.cadence.cadence_queue_job.exception;

public class CadenceQueueException extends Exception {
	private static final long serialVersionUID = 1829340443868224365L;	
	
	public CadenceQueueException(String errorMessage, Throwable e) {
		super(errorMessage, e);
	}

}
