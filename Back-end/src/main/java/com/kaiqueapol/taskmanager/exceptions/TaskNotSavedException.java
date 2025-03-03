package com.kaiqueapol.taskmanager.exceptions;

public class TaskNotSavedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TaskNotSavedException() {
		super("Task not saved!");
	}

	public TaskNotSavedException(String message) {
		super(message);
	}

}
