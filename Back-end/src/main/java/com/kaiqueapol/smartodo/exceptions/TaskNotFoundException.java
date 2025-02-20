package com.kaiqueapol.smartodo.exceptions;

public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TaskNotFoundException() { 
		super("Task not found!");
	}
	
	public TaskNotFoundException(String message) {
		super(message);
	}
	
	
}
