package com.example.taskmanager.service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.Task.Status;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public Task createTask(Task task) {
        task.setStatus(Status.PENDING);
        return repository.save(task);
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(int id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<Task> getTasksByStatus(Status status) {
        return repository.findByStatus(status);
    }

    public List<Task> getOverdueTasks() {
        return repository.findByDueDateBefore(LocalDate.now());
    }

    public Task updateTask(int id, Task updatedTask) {
        Task task = getTaskById(id);
        task.setName(updatedTask.getName());
        task.setDescription(updatedTask.getDescription());
        task.setDueDate(updatedTask.getDueDate());
        task.setStatus(updatedTask.getStatus());
        return repository.save(task);
    }

    public void deleteTask(int id) {
        Task task = getTaskById(id);
        task.setDeleted(true);
        repository.save(task);
    }

    public List<Task> getDeletedTasks() {
        return repository.findByDeleted(true);
    }

    public List<Task> getTasksSortedByDueDate() {
        return repository.findAll().stream()
                .sorted((a, b) -> a.getDueDate().compareTo(b.getDueDate()))
                .toList();
    }
}
