package com.example.springboot_mysql_project.controllers;

import com.example.springboot_mysql_project.dto.TaskDTO;
import com.example.springboot_mysql_project.dto.TaskResponseDTO;
import com.example.springboot_mysql_project.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
@Tag(name = "Task Management", description = "Operations related to managing tasks in the CRUD application")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // GET ALL TASKS
    @Operation(summary = "Get all tasks")
    @ApiResponse(responseCode = "200 OK", description = "Fetches all the tasks within the database")
    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    // CREATE A NEW TASK
    @Operation(summary = "Create a new task")
    @ApiResponse(responseCode = "201", description = "Task successfully created")
    @PostMapping
    public TaskResponseDTO createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    // MODIFYING AN EXISTING TASK
    @Operation(summary = "Update an existing task")
    @ApiResponse(responseCode = "200 OK", description = "Task updated successfully")
    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        taskDTO.setId(id); // Set ID from URL into the DTO
        return taskService.updateTask(taskDTO);
    }

    // DELETE A TASK
    @Operation(summary = "Delete an existing task")
    @ApiResponse(responseCode = "200 OK", description = "Task deleted successfully")
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }
}