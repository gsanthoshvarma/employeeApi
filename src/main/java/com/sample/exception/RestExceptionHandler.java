package com.sample.exception;

import java.util.Date;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sample.model.ErrorDetails;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value=EmployeeNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(EmployeeNotFoundException exception){
		ErrorDetails error = new ErrorDetails();
		error.setTimestamp(new Date().getTime());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setDetails(exception.getMessage());
		error.setMessage(exception.getClass().getName());
		return new ResponseEntity<>(error, null, HttpStatus.NOT_FOUND);
	}
}
