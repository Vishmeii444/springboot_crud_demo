package com.example.springboot_mysql_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepo taskRepo;

    // Get all the tasks available
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    // Add a new task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepo.save(task);
    }

    // Modify an existing task
    @PutMapping("/{id}")
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
    public void deleteTask(@PathVariable Long id) {
        taskRepo.deleteById(id);
    }
}