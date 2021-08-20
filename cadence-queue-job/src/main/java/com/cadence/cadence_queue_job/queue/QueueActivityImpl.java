package com.cadence.cadence_queue_job.queue;

import com.cadence.cadence_queue_job.dto.JobSucessInfoDto;
import com.cadence.cadence_queue_job.form.JobInfoForm;
import com.uber.cadence.workflow.Workflow;

public class QueueActivityImpl implements QueueActivity {

	@Override
	public JobSucessInfoDto executeJob(JobInfoForm form) {
		try {
			Thread.sleep(15000);
			return new JobSucessInfoDto(form.getJobId(), form.getDomain(), form.getJobId() + " executado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw Workflow.wrap(e);
		}
	}

}
