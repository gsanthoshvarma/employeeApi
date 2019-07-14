package com.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException() {
		super();
	}
	
	public EmployeeNotFoundException(String message) {
		super(message);
	}
	
	public EmployeeNotFoundException(String message,Throwable cause) {
		super(message,cause);
	}

}
