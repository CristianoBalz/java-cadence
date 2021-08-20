package com.cadence.cadence_queue_job.form;

import java.util.Arrays;

import com.cadence.cadence_queue_job.model.JobHistory;

public class HistoryFilterForm {
	private String field;
	private String value;	
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public FieldEnum getFieldEnum() {
		return FieldEnum.fromString(this.field);
	}
	
	public enum FieldEnum {
		DOMAIN, OID, TASK_LIST;		
		
		public boolean filter(JobHistory job, String value) {
			switch(this) {
			case DOMAIN:
				return job.getDomain().equals(value);	
			case OID:
				return job.getOid().equals(value);
			case TASK_LIST:
				return job.getTaskList().equals(value);
			default:
				return false;
			}
		}
		
		public static FieldEnum fromString(String field) {
			try {
				return valueOf(field);
	        } catch (Exception e) {            
	        	throw new IllegalArgumentException(field + " is invalid value. Supported values are " + Arrays.asList(values()));
	        }
		}
	}

	@Override
	public String toString() {
		return "HistoryFilterForm [field=" + field + ", value=" + value + "]";
	}
	
	
}
