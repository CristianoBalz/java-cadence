package com.cadence.cadence_queue_job.business;

import java.util.List;
import java.util.stream.Collectors;

import com.cadence.cadence_queue_job.Application;
import com.cadence.cadence_queue_job.dto.HistoryStatisticalDto;
import com.cadence.cadence_queue_job.form.HistoryFilterForm;
import com.cadence.cadence_queue_job.form.WorkflowIdentityForm;
import com.cadence.cadence_queue_job.model.JobHistory;
import com.cadence.cadence_queue_job.utils.StringUtils;
import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.internal.common.WorkflowExecutionUtils;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.IWorkflowService;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;

public class WorkflowHistoryBusiness {

	public String getWorkflowExecutionHistory(WorkflowIdentityForm form)  {
		validateWorkflowIdentityForm(form);
		
		IWorkflowService cadenceService = new WorkflowServiceTChannel(ClientOptions.defaultInstance());
		WorkflowExecution workflowExecution = new WorkflowExecution();
		workflowExecution.setWorkflowId(form.getWorkflowId());
		workflowExecution.setRunId(form.getRunId());
		return WorkflowExecutionUtils.prettyPrintHistory(cadenceService, form.getDomain(), workflowExecution, true);
	}
	
	public HistoryStatisticalDto getAllHistoryJob() {
		synchronized (Application.monitor) {
			return new HistoryStatisticalDto(Application.jobHistory.size(),Application.jobHistory);
		}
	}

	public void clearHistoryJob() {
		synchronized (Application.monitor) {							
			Application.jobHistory.clear();		
		}
	}	
	
	public HistoryStatisticalDto getAllHistoryByFilter(HistoryFilterForm form) {	
		validateHistoryFilterForm(form);
		synchronized (Application.monitor) {
			List<JobHistory> collect = Application.jobHistory.stream()
					.filter(job -> form.getFieldEnum().filter(job, form.getValue()))
					.collect(Collectors.toList());
			
			return new HistoryStatisticalDto(collect.size(),collect);
		}
	}
	
	private void validateWorkflowIdentityForm(WorkflowIdentityForm form) {
		if(form == null) {			
			throw new IllegalArgumentException("No data entered");
		}
		
		if(StringUtils.isNullOrEmpty(form.getDomain())) {
			throw new IllegalArgumentException("Attribute \"domain\" was not informed!");
		}
		
		if(StringUtils.isNullOrEmpty(form.getWorkflowId())) {
			throw new IllegalArgumentException("Attribute \"workflowId\" was not informed!");
		}
		
		if(StringUtils.isNullOrEmpty(form.getRunId())) {
			throw new IllegalArgumentException("Attribute \"runId\" was not informed!");
		}
	}
	
	private void validateHistoryFilterForm(HistoryFilterForm form) {
		if(form == null) {			
			throw new IllegalArgumentException("No data entered");
		}
		
		if(StringUtils.isNullOrEmpty(form.getValue())) {
			throw new IllegalArgumentException("Attribute \"value\" was not informed!");
		}
		
		if(StringUtils.isNullOrEmpty(form.getField())) {
			throw new IllegalArgumentException("Attribute \"field\" was not informed!");
		}	
		
		form.getFieldEnum();		
	}	
	
}
