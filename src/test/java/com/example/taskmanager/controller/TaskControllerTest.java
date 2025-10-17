package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SuppressWarnings("unused")
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllTasks() throws Exception {
        Task task = new Task();
        task.setId(1);
        task.setName("Test");
        Mockito.when(service.getAllTasks()).thenReturn(Collections.singletonList(task));

        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test"));
    }
    @Test
    void testCreateTask() throws Exception {
        Task task = new Task();
        task.setId(1);
        task.setName("Test Task");
        task.setDescription("Sample Description");
        task.setStatus(Task.Status.PENDING);

        // Mock service layer
        when(service.createTask(any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/api/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("Sample Description"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }
}
