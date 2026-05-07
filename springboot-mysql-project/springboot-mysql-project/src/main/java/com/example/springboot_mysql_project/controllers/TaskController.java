package com.example.springboot_mysql_project.controllers;

import com.example.springboot_mysql_project.entities.Task;
import com.example.springboot_mysql_project.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// lets Spring know that this class handles HTTP requests and returns JSON data
@RestController
// All the endpoints in this starts with /tasks
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepo taskRepo;

    // Get all the tasks available
    @GetMapping
    // this will basically carry out SELECT * FROM task
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    // Add a new task
    @PostMapping
    // this will carry out INSERT INTO task
    public Task createTask(@RequestBody Task task) {
        return taskRepo.save(task);
    }

    // Modify an existing task
    @PutMapping("/{id}")
    // this will carry out SELECT * FROM task WHERE id = smth
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        // Find a task in the database, throw an error if it doesn't exist
        Task existingTask = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        //Update the Java object with the new values
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());

        // Save it back to the database
        return taskRepo.save(existingTask);
    }

    // Remove a task
    @DeleteMapping("/{id}")
    // this will carry out DELETE FROM task WHERE id = smth
    public void deleteTask(@PathVariable Long id) {
        taskRepo.deleteById(id);
    }
}