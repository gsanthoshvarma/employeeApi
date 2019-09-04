package com.sample.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value=EmployeeNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(EmployeeNotFoundException exception){
		ErrorDetails error = new ErrorDetails();
		error.setTitle("Resource Not Found");
		error.setTimestamp(new Date().getTime());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setDetails(exception.getMessage());
		error.setException(exception.getClass().getName());
		return new ResponseEntity<>(error, null, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleException(MethodArgumentNotValidException exception){
		Map<String,List<ValidationError>> errorMap = new HashMap<>();
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTitle("validation failed");
		errorDetails.setTimestamp(new Date().getTime());
		errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
		errorDetails.setDetails("Input validation failed");
		errorDetails.setException(exception.getClass().getName());
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		for(FieldError obj : fieldErrors) {
			if(errorMap.get(obj.getField()) != null) {
				List<ValidationError> existErrors = errorMap.get(obj.getField());
				ValidationError error = new ValidationError();
				error.setCode(obj.getCode());
				error.setMessage(obj.getDefaultMessage());
				existErrors.add(error);
			}else {
				List<ValidationError> list = new ArrayList<>();
				ValidationError validationError = new ValidationError();
				validationError.setCode(obj.getCode());
				validationError.setMessage(obj.getDefaultMessage());
				list.add(validationError);
				errorDetails.getErrors().put(obj.getField(), list);
			}
		}
		return new ResponseEntity<>(errorDetails, null, HttpStatus.BAD_REQUEST);
		
	}
}
