package com.cadence.cadence_queue_job.queue;


import com.cadence.cadence_queue_job.dto.JobSucessInfoDto;
import com.cadence.cadence_queue_job.form.JobInfoForm;
import com.uber.cadence.activity.ActivityMethod;

public interface QueueActivity {
	
	@ActivityMethod(scheduleToCloseTimeoutSeconds = 60)
	JobSucessInfoDto executeJob(JobInfoForm form);
}
