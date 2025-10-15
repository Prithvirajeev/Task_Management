package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.Task.Status;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/tasks")
@Api(value = "Task Management System", description = "Operations pertaining to tasks in Task Management System")
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping
    @ApiOperation(value = "Create a new task")
    public Task createTask(@RequestBody Task task) {
        return service.createTask(task);
    }

    @GetMapping
    @ApiOperation(value = "Retrieve all tasks")
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrieve a task by its ID")
    public Task getTaskById(@PathVariable int id) {
        return service.getTaskById(id);
    }

    @GetMapping("/status")
    @ApiOperation(value = "Retrieve tasks by status")
    public List<Task> getTasksByStatus(@RequestParam Status status) {
        return service.getTasksByStatus(status);
    }

    @GetMapping("/overdue")
    @ApiOperation(value = "Retrieve overdue tasks")
    public List<Task> getOverdueTasks() {
        return service.getOverdueTasks();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a task by its ID")
    public Task updateTask(@PathVariable int id, @RequestBody Task task) {
        return service.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a task by its ID (soft delete)")
    public String deleteTask(@PathVariable int id) {
        service.deleteTask(id);
        return "Task marked as deleted";
    }

    @GetMapping("/deleted")
    @ApiOperation(value = "Retrieve all deleted tasks")
    public List<Task> getDeletedTasks() {
        return service.getDeletedTasks();
    }

    @GetMapping("/sort")
    @ApiOperation(value = "Retrieve tasks sorted by due date")
    public List<Task> getTasksSortedByDueDate() {
        return service.getTasksSortedByDueDate();
    }
}
