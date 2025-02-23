package com.kaiqueapol.taskmanager.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaiqueapol.taskmanager.entities.Task;
import com.kaiqueapol.taskmanager.services.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

	@InjectMocks
	TaskController taskController;

	@Mock
	TaskService taskService;

	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	private String json;

	private Task task;

	@BeforeEach
	void setup() throws JsonProcessingException {
		mockMvc = MockMvcBuilders.standaloneSetup(taskController).alwaysDo(print()).build();
		task = Task.builder().id(1L).name("TaskName").finished(false).build();
		json = objectMapper.writeValueAsString(task);
	}

	@Test
	void shouldFindAllTasks() throws Exception {
		List<Task> tasks = List.of(new Task(1L, "TestTask", false));
		String tasksJson = objectMapper.writeValueAsString(tasks);
		when(taskService.findAll()).thenReturn(tasks);

		mockMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(tasksJson));

		verify(taskService).findAll();
		verifyNoMoreInteractions(taskService);
	}

	@Test
	void shouldNotFindAllTasksAndReturnNotFound() throws Exception {
		List<Task> tasks = List.of();
		when(taskService.findAll()).thenReturn(tasks);

		mockMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());

		verify(taskService).findAll();
		verifyNoMoreInteractions(taskService);
	}

	@Test
	void shouldFindSpecificTask() throws Exception {
		when(taskService.findById(task.getId())).thenReturn(task);

		mockMvc.perform(get("/" + task.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(json));

		verify(taskService).findById(task.getId());
		verifyNoMoreInteractions(taskService);
	}

	@Test
	void shouldNotFindSpecificTaskAndReturnNotFound() throws Exception {
		when(taskService.findById(task.getId())).thenReturn(null);

		mockMvc.perform(get("/" + task.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

		verify(taskService).findById(task.getId());
		verifyNoMoreInteractions(taskService);
	}

	@Test
	void shouldSaveTask() throws Exception {
		when(taskService.save(task)).thenReturn(task);

		mockMvc.perform(post("/save").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk())
				.andExpect(content().json(json));

		verify(taskService).save(task);
		verifyNoMoreInteractions(taskService);

	}

	@Test
	void shouldNotSaveTaskAndReturnBadRequest() throws Exception {
		mockMvc.perform(post("/save").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest());

		verify(taskService).save(task);
		verifyNoMoreInteractions(taskService);

	}

	@Test
	void shouldUpdateTask() throws Exception {
		Task substituteTask = new Task(2L, "Substitute_Task", false);
		String substituteTaskJson = objectMapper.writeValueAsString(substituteTask);
		when(taskService.updateTask(task.getId(), substituteTask)).thenReturn(task);

		mockMvc.perform(put("/" + task.getId()).contentType(MediaType.APPLICATION_JSON).content(substituteTaskJson))
				.andExpect(status().isOk()).andExpect(content().json(json));

		verify(taskService).updateTask(task.getId(), substituteTask);
		verifyNoMoreInteractions(taskService);

	}

	@Test
	void shouldNotUpdateTaskAndReturnBadRequest() throws Exception {
		Task substituteTask = new Task(2L, "Substitute_Task", false);
		String substituteTaskJson = objectMapper.writeValueAsString(substituteTask);

		mockMvc.perform(put("/" + task.getId()).contentType(MediaType.APPLICATION_JSON).content(substituteTaskJson))
				.andExpect(status().isBadRequest());

		verify(taskService).updateTask(task.getId(), substituteTask);
		verifyNoMoreInteractions(taskService);

	}

	@Test
	void shouldDeleteTask() throws Exception {
		doNothing().when(taskService).deleteTask(task.getId());

		mockMvc.perform(delete("/" + task.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(taskService).deleteTask(task.getId());
		verifyNoMoreInteractions(taskService);
	}

	@Test
	void shouldNotDeleteTaskAndReturnNotFound() throws Exception {
		mockMvc.perform(delete("/" + task.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

		verify(taskService).deleteTask(task.getId());
		verifyNoMoreInteractions(taskService);
	}
}
