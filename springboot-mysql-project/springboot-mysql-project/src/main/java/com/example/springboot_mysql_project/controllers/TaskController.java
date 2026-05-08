package com.example.springboot_mysql_project.controllers;

import com.example.springboot_mysql_project.entities.Task;
import com.example.springboot_mysql_project.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// lets Spring know that this class handles HTTP requests and returns JSON data
@RestController
// All the endpoints in this starts with /tasks
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Get all the tasks available
    @GetMapping
    // this will basically carry out SELECT * FROM task
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // Add a new task
    @PostMapping
    // this will carry out INSERT INTO task
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    // Modify an existing task
    @PutMapping("/{id}")
    // this will carry out SELECT * FROM task WHERE id = smth
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    // Remove a task
    @DeleteMapping("/{id}")
    // this will carry out DELETE FROM task WHERE id = smth
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }
}