package com.kaiqueapol.smartodo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaiqueapol.smartodo.entities.Task;
import com.kaiqueapol.smartodo.services.TaskService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/")
public class TaskController {
	private TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping
	public ResponseEntity<List<Task>> findAll() {
		List<Task> list = taskService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Task> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(taskService.findById(id));
	}

	@PostMapping("/save")
	public ResponseEntity<Task> save(@RequestBody Task task) {
		return ResponseEntity.ok(taskService.save(task));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
		return ResponseEntity.ok().body(taskService.updateTask(id, updatedTask));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}

}
