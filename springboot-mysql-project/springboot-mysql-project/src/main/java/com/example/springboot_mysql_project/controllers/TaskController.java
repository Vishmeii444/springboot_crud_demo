package com.example.springboot_mysql_project.controllers;

import com.example.springboot_mysql_project.dto.TaskDTO;
import com.example.springboot_mysql_project.dto.TaskResponseDTO;
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
// All the endpoints in here starts with /tasks
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
    // this will basically carry out SELECT * FROM task
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    // CREATE A NEW TASK
    @Operation(summary = "Create a new task")
    @ApiResponse(responseCode = "201", description = "Task successfully created")
    @PostMapping
    // this will carry out INSERT INTO task
    public TaskResponseDTO createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }
    // MODIFYING AN EXISTING TASK
    @Operation(summary = "Update an existing task")
    @ApiResponse(responseCode = "200 OK", description = "Task updated successfully")
    @PutMapping("/{id}")
    // this will carry out SELECT * FROM task WHERE id = smth
    public TaskResponseDTO updateTask(@RequestBody TaskDTO taskDTO) {
        return taskService.updateTask(taskDTO);
    }

    // DELETE A TASK
    @Operation(summary = "Delete an existing task")
    @ApiResponse(responseCode = "200 OK", description = "Task deleted successfully")
    @DeleteMapping("/{id}")
    // this will carry out DELETE FROM task WHERE id = smth
    public String deleteTask(@RequestBody TaskDTO taskDTO) {
        return taskService.deleteTask(taskDTO);
    }
}