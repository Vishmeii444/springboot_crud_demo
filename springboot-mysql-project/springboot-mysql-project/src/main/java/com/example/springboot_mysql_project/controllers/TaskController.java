package com.example.springboot_mysql_project.controllers;

import com.example.springboot_mysql_project.entities.Task;
import com.example.springboot_mysql_project.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// lets Spring know that this class handles HTTP requests and returns JSON data
@RestController
// All the endpoints in this starts with /tasks

@RequestMapping("/tasks")
@Tag(name = "Task Management", description = "Operations related to managing tasks in the CRUD application")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Get all tasks")
    @ApiResponse(responseCode = "200 OK", description = "Fetches all the tasks within the database")
    // Get all the tasks available
    @GetMapping
    // this will basically carry out SELECT * FROM task
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Operation(summary = "Create a new task")
    @ApiResponse(responseCode = "201", description = "Task successfully created")
    // Add a new task
    @PostMapping
    // this will carry out INSERT INTO task
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @Operation(summary = "Update an existing task")
    @ApiResponse(responseCode = "200 OK", description = "Task updated successfully")
    // Modify an existing task
    @PutMapping("/{id}")
    // this will carry out SELECT * FROM task WHERE id = smth
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    @Operation(summary = "Delete an existing task")
    @ApiResponse(responseCode = "200 OK", description = "Task deleted successfully")
    // Remove a task
    @DeleteMapping("/{id}")
    // this will carry out DELETE FROM task WHERE id = smth
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }
}