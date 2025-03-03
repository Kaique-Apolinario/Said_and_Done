package com.kaiqueapol.taskmanager.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kaiqueapol.taskmanager.entities.Task;
import com.kaiqueapol.taskmanager.exceptions.TaskNotFoundException;
import com.kaiqueapol.taskmanager.exceptions.TaskNotSavedException;
import com.kaiqueapol.taskmanager.repositories.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

	@InjectMocks
	TaskService taskService;

	@Mock
	TaskRepository taskRepository;

	private Task task;

	@BeforeEach
	void setup() {
		task = Task.builder().id(1L).name("Test Task").finished(false).build();
	}

	@Test
	void shouldFindAllTasks() {
		List<Task> taskList = taskService.findAll();

		verify(taskRepository).findAll();
		assertNotNull(taskList);
	}

	@Test
	void shouldNotFindAllTasks() {
		when(taskRepository.findAll()).thenThrow(new TaskNotFoundException());

		assertThrows(TaskNotFoundException.class, () -> taskService.findAll());

		verify(taskRepository).findAll();
		verifyNoMoreInteractions(taskRepository);
	}

	@Test
	void shouldFindSpecificTask() {
		when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
		Task taskFromRepo = taskService.findById(task.getId());

		assertNotNull(taskFromRepo);
		verify(taskRepository).findById(task.getId());
		assertEquals(task.getId(), taskFromRepo.getId());
	}

	@Test
	void shouldNotFindSpecificTask() {
		when(taskRepository.findById(task.getId())).thenThrow(new TaskNotFoundException());

		assertThrows(TaskNotFoundException.class, () -> taskService.findById(task.getId()));
		verify(taskRepository).findById(task.getId());
		verifyNoMoreInteractions(taskRepository);
	}

	@Test
	void shouldSaveTask() {
		when(taskRepository.save(task)).thenReturn(task);
		Task taskFromRepo = taskService.save(task);

		assertNotNull(taskFromRepo);
		verify(taskRepository).save(task);
		assertEquals(task.getId(), taskFromRepo.getId());
	}

	@Test
	void shouldNotSaveTask() {
		when(taskRepository.save(task)).thenThrow(new TaskNotSavedException());

		assertThrows(TaskNotSavedException.class, () -> taskService.save(task));
		verify(taskRepository).save(task);
		verifyNoMoreInteractions(taskRepository);
	}

	@Test
	void shouldUpdateTask() {
		System.out.println("First version of 'task': " + task.toString());

		Task newTask = Task.builder().id(2L).name("Name Changed").finished(true).build();
		when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
		when(taskRepository.save(task)).thenReturn(task);

		Task updatedTask = taskService.updateTask(task.getId(), newTask);

		System.out.println("Updated version of 'task': " + task.toString());

		assertNotNull(updatedTask);
		assertEquals(updatedTask.getName(), newTask.getName());
		assertNotEquals(updatedTask.getId(), newTask.getId());
		assertEquals(updatedTask.getId(), task.getId());

	}

	@Test
	void shouldNotUpdateTask() {
		Task newTask = Task.builder().id(2L).name("Name Changed").finished(true).build();
		when(taskRepository.findById(task.getId())).thenThrow(new TaskNotFoundException());

		assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(task.getId(), newTask));
		verify(taskRepository, times(0)).save(task);
		verifyNoMoreInteractions(taskRepository);

	}

	@Test
	void shouldDeleteTask() {
		doNothing().when(taskRepository).deleteById(task.getId());
		taskRepository.deleteById(task.getId());

		verify(taskRepository).deleteById(task.getId());

	}

	@Test
	void shouldNotDeleteTask() {
		when(taskRepository.findById(task.getId())).thenThrow(new TaskNotFoundException());

		assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(task.getId()));
		verify(taskRepository, times(0)).deleteById(task.getId());
		verifyNoMoreInteractions(taskRepository);

	}
}
