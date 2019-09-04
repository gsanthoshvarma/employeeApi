package com.sample.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetails {
	
	private String title;
	private int status;
	private String details;
	private long timestamp;
	private String exception;
	private Map<String, List<ValidationError>> errors = new HashMap<>();
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Map<String, List<ValidationError>> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, List<ValidationError>> errors) {
		this.errors = errors;
	}
	
	

}
