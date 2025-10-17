package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.Task.Status;
import com.example.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {
    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Task();
        task.setId(1);
        task.setName("Test Task");
        task.setDescription("This is a sample task description.");
        task.setDueDate(LocalDate.now().plusDays(1));
        task.setStatus(Status.PENDING);
    }

    @Test
    void testCreateTask() {
        // Test implementation goes here
        when(repository.save(task)).thenReturn(task);
        Task result = service.createTask(task);
        assertEquals("Test Task", result.getName());
        verify(repository, times(1)).save(task);
    }
    
    @Test
    void testGetTaskById() {
        when(repository.findById(1)).thenReturn(Optional.of(task));
        Task result = service.getTaskById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testGetAllTasks() {
        when(repository.findAll()).thenReturn(Arrays.asList(task));
        List<Task> list = service.getAllTasks();
        assertEquals(1, list.size());
    }

    @Test
    void testDeleteTask() {
        when(repository.findById(1)).thenReturn(Optional.of(task));
        service.deleteTask(1);
        verify(repository, times(1)).save(task);
    }
}
