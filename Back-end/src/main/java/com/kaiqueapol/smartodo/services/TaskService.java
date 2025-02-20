package com.kaiqueapol.smartodo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kaiqueapol.smartodo.entities.Task;
import com.kaiqueapol.smartodo.exceptions.TaskNotFoundException;
import com.kaiqueapol.smartodo.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskService {
	private TaskRepository taskRepo;

	public TaskService(TaskRepository taskRepo) {
		this.taskRepo = taskRepo;
	}

	public List<Task> findAll() {
		List<Task> taskList = taskRepo.findAll();
		if (taskList.isEmpty()) {
			throw new TaskNotFoundException("Tasks not found.");
	} else {
		return taskList;
		}
	}
	

	public Task findById(Long id) {
		Optional<Task> task = taskRepo.findById(id);
		if (task.isEmpty()) {
			throw new TaskNotFoundException();
	} else {
		return task.get();
		}
	}

	public Task save(Task task) {
		return taskRepo.save(task);
	}

	public Task updateTask(Long id, Task task) {
		Optional<Task> oldTask = taskRepo.findById(id);
		oldTask.get().setName(task.getName());
		oldTask.get().setFinished(task.isFinished());
		return taskRepo.save(oldTask.get());
	}

	public void deleteTask(Long id) {
		taskRepo.deleteById(id);
	}
}
