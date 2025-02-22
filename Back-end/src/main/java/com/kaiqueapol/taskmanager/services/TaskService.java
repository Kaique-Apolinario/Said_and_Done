package com.kaiqueapol.taskmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kaiqueapol.taskmanager.entities.Task;
import com.kaiqueapol.taskmanager.exceptions.TaskNotFoundException;
import com.kaiqueapol.taskmanager.repositories.TaskRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class TaskService {
	private TaskRepository taskRepo;

	public TaskService(TaskRepository taskRepo) {
		this.taskRepo = taskRepo;
	}

	public List<Task> findAll() {
		List<Task> taskList = taskRepo.findAll();
		if (taskList.isEmpty()) {
			log.error("Couldn't find any task.");
			throw new TaskNotFoundException("Tasks not found.");
		} else {
			log.info("Tasks retrieved.");
			return taskList;
		}
	}

	public Task findById(Long id) {
		Optional<Task> task = taskRepo.findById(id);
		if (task.isEmpty()) {
			log.error("Couldn't find task with ID: " + id);
			throw new TaskNotFoundException();
		} else {
			log.info("Task with ID " + id + " retrieved.");
			return task.get();
		}
	}

	public Task save(Task task) {
		log.info("Task with ID " + task.getId() + " saved sucessfully.");
		return taskRepo.save(task);
	}

	public Task updateTask(Long id, Task task) {
		Optional<Task> oldTask = taskRepo.findById(id);
		oldTask.get().setName(task.getName());
		oldTask.get().setFinished(task.isFinished());
		log.info("Task with ID " + oldTask.get().getId() + " sucessfully updated.");
		return taskRepo.save(oldTask.get());
	}

	public void deleteTask(Long id) {
		taskRepo.deleteById(id);
		log.info("Task with ID " + id + " sucessfully deleted.");
	}
}
