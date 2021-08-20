package com.cadence.cadence_queue_job.business;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.cadence.cadence_queue_job.dto.RegisterDomainDto;
import com.cadence.cadence_queue_job.exception.CadenceQueueException;
import com.cadence.cadence_queue_job.utils.StringUtils;
import com.uber.cadence.DomainAlreadyExistsError;
import com.uber.cadence.RegisterDomainRequest;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.IWorkflowService;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;

public class DomainBusiness {
	
	private static final int RETENTION_PERIOD_IN_DAYS = 1;

	public RegisterDomainDto registerDomain(String id) throws CadenceQueueException {		
		if(StringUtils.isNullOrEmpty(id)) {
			throw new IllegalArgumentException("Domain not informed!");
		}
		
		IWorkflowService cadenceService = new WorkflowServiceTChannel(ClientOptions.defaultInstance());
	    RegisterDomainRequest request = new RegisterDomainRequest();
	    request.setDescription(id);
	    request.setEmitMetric(false);
	    request.setName(id);
	    request.setWorkflowExecutionRetentionPeriodInDays(RETENTION_PERIOD_IN_DAYS);
	    try {
	      cadenceService.RegisterDomain(request);
	    } catch (DomainAlreadyExistsError e) {	      
	    	throw new CadenceQueueException("Domain \"" + id + "\" is already registered",e);
	    } catch (Exception e) {
	    	throw new CadenceQueueException("Fail to register domain \""+id+"\"",e);
	    }
		
	    return new RegisterDomainDto(id, RETENTION_PERIOD_IN_DAYS);
	}
	
	public List<RegisterDomainDto> registerThreeSampleDomains() {
		return getSampleDomains().stream().map(s -> {
			try {
				return registerDomain(s);
			} catch (CadenceQueueException e) {				
				return null;
			}
		})
		.collect(Collectors.toList());	
			
	}
	
	public List<String> getSampleDomains(){
		return Stream.of("1","2")
				.map(i -> "domain_0"+i)
				.collect(Collectors.toList());
	}

}
