package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.Task.Status;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        return service.getTaskById(id);
    }

    @GetMapping("/status")
    public List<Task> getTasksByStatus(@RequestParam Status status) {
        return service.getTasksByStatus(status);
    }

    @GetMapping("/overdue")
    public List<Task> getOverdueTasks() {
        return service.getOverdueTasks();
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task task) {
        return service.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        service.deleteTask(id);
        return "Task marked as deleted";
    }

    @GetMapping("/deleted")
    public List<Task> getDeletedTasks() {
        return service.getDeletedTasks();
    }

    @GetMapping("/sort")
    public List<Task> getTasksSortedByDueDate() {
        return service.getTasksSortedByDueDate();
    }
}
