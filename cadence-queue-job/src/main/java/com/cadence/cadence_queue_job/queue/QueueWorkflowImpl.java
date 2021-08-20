package com.cadence.cadence_queue_job.queue;

import java.util.Date;

import com.cadence.cadence_queue_job.Application;
import com.cadence.cadence_queue_job.dto.JobSucessInfoDto;
import com.cadence.cadence_queue_job.form.JobInfoForm;
import com.cadence.cadence_queue_job.model.JobHistory;
import com.cadence.cadence_queue_job.model.TypeWorkflowExecution;
import com.uber.cadence.workflow.Workflow;

public class QueueWorkflowImpl implements QueueWorkflow {	
	
	private final QueueActivity activities =
	        Workflow.newActivityStub(QueueActivity.class);

    @Override
    public JobSucessInfoDto executeJob(JobInfoForm form, String oid, TypeWorkflowExecution type) {
    	try
    	{
	    	JobSucessInfoDto executeJob = activities.executeJob(form);
	    	JobHistory createJobHistoryObj = createJobHistoryObj(executeJob, oid, type, form.getTaskList());
			synchronized (Application.monitor) {
				Application.jobHistory.add(createJobHistoryObj);				
			}
	     	System.out.println(createJobHistoryObj);
	    	return executeJob;
    	} catch(Exception e) {
    		throw Workflow.wrap(e);
    	}
    }
    
    private JobHistory createJobHistoryObj(JobSucessInfoDto sucessInfoDto, 
    		String oid, TypeWorkflowExecution type, String taskList) {
    	JobHistory jobHistory = new JobHistory();
    	jobHistory.setDomain(sucessInfoDto.getDomain());
    	jobHistory.setJobId(sucessInfoDto.getJobId());
    	jobHistory.setProcess(new Date());
    	jobHistory.setSucess(true);
    	jobHistory.setOid(oid);
    	jobHistory.setType(type);
    	jobHistory.setMessage(sucessInfoDto.getMessage());
    	jobHistory.setTaskList(taskList);
    	return jobHistory;
    }

}
