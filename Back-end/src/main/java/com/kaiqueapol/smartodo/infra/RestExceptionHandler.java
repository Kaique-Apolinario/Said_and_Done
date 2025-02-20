package com.kaiqueapol.smartodo.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kaiqueapol.smartodo.exceptions.TaskNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(TaskNotFoundException.class)
	private ResponseEntity<String> taskNotFoundHandler(TaskNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
	}
	
	
	
}
