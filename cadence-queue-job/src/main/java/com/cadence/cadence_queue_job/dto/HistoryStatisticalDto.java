package com.cadence.cadence_queue_job.dto;

import java.util.List;

import com.cadence.cadence_queue_job.model.JobHistory;

public class HistoryStatisticalDto {
	private Integer totRegisters;
	private List<JobHistory> registers;
	
	public HistoryStatisticalDto(Integer totRegisters, List<JobHistory> registers) {
		this.totRegisters = totRegisters;
		this.registers = registers;
	}

	public Integer getTotRegisters() {
		return totRegisters;
	}	
	
	public List<JobHistory> getRegisters() {
		return registers;
	}	

	@Override
	public String toString() {
		return "HistoryStatisticalDto [totRegisters=" + totRegisters + ", registers=" + registers + "]";
	}	
	
}
