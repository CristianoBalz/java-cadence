package com.cadence.cadence_queue_job.dto;

public class RegisterDomainDto {
	String name;
	Integer durationInDays;
	
	public RegisterDomainDto(String name, Integer durationInDays) {
		this.name = name;
		this.durationInDays = durationInDays;
	}

	public String getName() {
		return name;
	}
		
	public Integer getDurationInDays() {
		return durationInDays;
	}	
	
	@Override
	public String toString() {
		return "RegisterDomainDto [name=" + name + ", durationInDays=" + durationInDays + "]";
	}	
}
